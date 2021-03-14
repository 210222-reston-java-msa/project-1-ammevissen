package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class ManagerDAOImpl implements ManagerDAO {
	
	private static Logger log = Logger.getLogger(ManagerDAOImpl.class);


	public static List<Object> managerView(int view){
		List<Object> employeeReimbursements= new ArrayList<Object>();
		
		try {
			Connection conn=ConnectionUtil.getConnection();
		
			PreparedStatement pstmt;
			
			if (view==0) {				
				String reimbursement="SELECT * "
						+ "from ers_reimbursement "
						+ "left join ers_users "
						+ "on ers_reimbursement.reimb_resolver=ers_users.ers_users_id";


				 pstmt= conn.prepareStatement(reimbursement);
				log.debug("pstmt created");
				
			}else if(view==5){
				String reimbursement="SELECT * "
						+ "from ers_reimbursement "
						+ "left join ers_users "
						+ "on ers_reimbursement.reimb_resolver=ers_users.ers_users_id "
						+ "where REIMB_STATUS_ID = 2 OR REIMB_STATUS_ID = 3 "; //2+3=5
				 
				pstmt= conn.prepareStatement(reimbursement);
				log.debug("pstmt created");
				
			}else {
				
				String reimbursement="SELECT * "
						+ "from ers_reimbursement "
						+ "left join ers_users "
						+ "on ers_reimbursement.reimb_resolver=ers_users.ers_users_id "
						+ "where REIMB_STATUS_ID =? ";

				
				 pstmt= conn.prepareStatement(reimbursement);
				log.debug("pstmt created");
			
				pstmt.setInt(1, view);
			}
			
			ResultSet result=pstmt.executeQuery();
			log.debug("Query executed");
			
			
			//https://howtodoinjava.com/java/date-time/convert-string-to-localdate/			
			while(result.next()) {
				List<Object> employeeReimbursement= new ArrayList<Object>();
				employeeReimbursement.add(result.getInt("reimb_id"));
				employeeReimbursement.add(result.getDouble("reimb_amount"));
				
				
				if (result.getObject("reimb_submitted", LocalDate.class)!=null) {
					
					String date=result.getObject("reimb_submitted", LocalDate.class).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					employeeReimbursement.add(date);
					
				}else {
					employeeReimbursement.add(null);
				}
				
				if (result.getObject("reimb_resolved", LocalDate.class)!=null) {
					
					String date=result.getObject("reimb_resolved", LocalDate.class).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					employeeReimbursement.add(date);
					
				}else {
					employeeReimbursement.add(null);
				}
				
//				employeeReimbursement.add(result.getObject("reimb_submitted", LocalDate.class));
//				employeeReimbursement.add(result.getObject("reimb_resolved", LocalDate.class));
				employeeReimbursement.add(result.getString("reimb_description"));
				
				
				
				String lastName="SELECT ers_last_name "
						+ "FROM ers_users "
						+ "WHERE ers_users_id=?";
				PreparedStatement pstmt2= conn.prepareStatement(lastName);
				pstmt2.setInt(1, result.getInt("reimb_author"));
				ResultSet lName=pstmt2.executeQuery();
				lName.next();
				employeeReimbursement.add(lName.getString("ers_last_name"));
				//employeeReimbursement.add(result.getInt("reimb_author"));
				
				//use merge table to cut down on calls to db
//				if (result.getInt("reimb_resolver")==0) {
//					employeeReimbursement.add("null");
//				}else {
//					String lastName="SELECT ers_last_name "
//							+ "FROM ers_users "
//							+ "WHERE ers_users_id=?";
//					PreparedStatement pstmt2= conn.prepareStatement(lastName);
//					pstmt2.setInt(1, result.getInt("reimb_resolver"));
//					ResultSet lName=pstmt2.executeQuery();
//					lName.next();
//					employeeReimbursement.add(lName.getString("ers_last_name"));
//					
//				}
				//employeeReimbursement.add(result.getInt("reimb_resolver"));
				employeeReimbursement.add(result.getString("ers_last_name"));
				employeeReimbursement.add(result.getInt("reimb_status_id"));
				employeeReimbursement.add(result.getInt("reimb_type_id"));
				
				log.debug(employeeReimbursement);
				employeeReimbursements.add(employeeReimbursement);
			}
			
			log.debug(employeeReimbursements);
			
		}catch (SQLException ex) {
			log.warn("Unable insert reimbursement into the database", ex);
		}
		
		return employeeReimbursements;
	}

	
	public static List<Object> viewEmployees(){
		List<Object> employees= new ArrayList<Object>();
		
		try {
			Connection conn=ConnectionUtil.getConnection();
		
			PreparedStatement pstmt;
			
			String people="SELECT * "
						+ "from ers_users";

			pstmt= conn.prepareStatement(people);
			log.debug("pstmt created");
			
			
			ResultSet result=pstmt.executeQuery();
			log.debug("Query executed");
			
			
			//https://howtodoinjava.com/java/date-time/convert-string-to-localdate/			
			while(result.next()) {
				List<Object> employee= new ArrayList<Object>();
				employee.add(result.getString("ers_first_name"));
				employee.add(result.getString("ers_last_name"));
				employee.add(result.getInt("ers_users_id"));
				employee.add(result.getString("ers_username"));
				employee.add(result.getString("ers_email"));
				employee.add(result.getInt("ers_role_id"));
				
				log.debug(employee);
				employees.add(employee);
			}
			
			log.debug(employees);
			
		}catch (SQLException ex) {
			log.warn("Unable insert reimbursement into the database", ex);
		}
		
		return employees;
	}
	
	public static int reimApprove(int approve, int e,  int ReimId) {
		
		try {
			Connection conn=ConnectionUtil.getConnection();
		
			String reimbursement="UPDATE ers_reimbursement "
							+ "set REIMB_STATUS_ID =?, "
							+ "reimb_resolver=?, "
							+ "reimb_resolved=current_date "
							+ "where REIMB_ID =?";

			PreparedStatement pstmt = conn.prepareStatement(reimbursement);
			log.debug("pstmt created");
		
			pstmt.setInt(1, approve);
			pstmt.setInt(2, e);
			pstmt.setInt(3, ReimId);
			
			log.debug("executing update");
			return pstmt.executeUpdate();
		}catch (SQLException ex) {
			log.warn("Unable update reimbursement database", ex);
			return 0;
		}
		
	}
	
	
}
