package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;


import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static Logger log = Logger.getLogger(EmployeeDAO.class);
	
	@Override
	public Employee findByUsername(String username) {
		Employee e=new Employee();
		
		try {
			Connection conn=ConnectionUtil.getConnection();
			
			//Setting the PreparedStatment
			String getEmployee="SELECT * FROM ers_users WHERE ers_username=?";
			PreparedStatement pstmt = conn.prepareStatement(getEmployee);
			pstmt.setString(1, username);
			
			//Executing the call to SQL
			log.debug("about to execute Query for user: "+username);
			ResultSet employee=pstmt.executeQuery();
			log.debug("Query executed");

			//Retrieving the password from the call to SQL
			if(employee.next()) {
				log.debug("employee.next()");
				e.setEmail(employee.getString("ers_email"));
				e.setFirstName(employee.getString("ers_first_name"));
				e.setLastName(employee.getString("ers_last_name"));
				e.setPassword(employee.getString("ers_password"));
				e.setRoleId(employee.getInt("ers_role_id"));
				e.setUserId(employee.getInt("ers_users_id"));
				e.setUsername(employee.getString("ers_username"));
			}else {
				e=null;
			}
			
		}catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);
		}
		
		return e;
	}
	
	@Override
	public int insertEmployeeReimbursement(Reimbursement reim) {

		try {
			Connection conn=ConnectionUtil.getConnection();
			//Setting the PreparedStatment
			String reimbursment="INSERT INTO ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id)"
								+"VALUES(?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(reimbursment);
			log.debug("pstmt created");
		
			pstmt.setDouble(1, reim.getAmount());
			pstmt.setString(2, reim.getDescription());
			pstmt.setInt(3, reim.getAuthor());
			pstmt.setInt(4, reim.getStatusId());
			pstmt.setInt(5, reim.getTypeId());
		
			//Executing the call to SQL
			log.debug("about to execute update for Reimbursement: "+reim);
			return pstmt.executeUpdate();
			
		}catch (SQLException ex) {
			log.warn("Unable insert reimbursement into the database", ex);
			return 0;
		}
	}
	
	@Override
	public int insertEmployeeUpdate(Employee e) {
		try {
			Connection conn=ConnectionUtil.getConnection();
		
			//Setting the PreparedStatment
			String reimbursement="UPDATE ers_users "
					+ "SET ers_password= ?, "
					+ "ers_first_name= ?, "
					+ "ers_last_name= ? "
					+ "WHERE ers_users_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(reimbursement);
			log.debug("pstmt created");
		
			pstmt.setString(1, e.getPassword());
			pstmt.setString(2, e.getFirstName());
			pstmt.setString(3, e.getLastName());
			pstmt.setInt(4, e.getUserId());
			
			//Executing the call to SQL
			log.debug("about to execute update for employee: "+e.getUsername());
			return pstmt.executeUpdate();
		}catch (SQLException ex) {
			log.warn("Unable insert reimbursement into the database", ex);
			return 0;
		}
		
		
	}
	
	@Override
	public List<Object> employeeView(int e, int view){
		List<Object> employeeReimbursements= new ArrayList<Object>();
		
		try {
			Connection conn=ConnectionUtil.getConnection();
		
			//Setting the PreparedStatment
			PreparedStatement pstmt;
			
			if (view==0) {
//				String reimbursement="SELECT * "
//						+ "from ers_reimbursement "
//						+ "where reimb_author =? ";
				
				String reimbursement="SELECT * "
						+ "from ers_reimbursement "
						+ "left join ers_users "
						+ "on ers_reimbursement.reimb_resolver=ers_users.ers_users_id "
						+ "where reimb_author =? ";


				 pstmt= conn.prepareStatement(reimbursement);
				log.debug("pstmt created");
			
				pstmt.setInt(1, e);
				
			}else {
				
//				String reimbursement="SELECT * "
//						+ "from ers_reimbursement "
//						+ "where reimb_author =? AND REIMB_STATUS_ID =? ";

				String reimbursement="SELECT * "
						+ "from ers_reimbursement "
						+ "left join ers_users "
						+ "on ers_reimbursement.reimb_resolver=ers_users.ers_users_id "
						+ "where reimb_author =? AND REIMB_STATUS_ID =? ";

				
				 pstmt= conn.prepareStatement(reimbursement);
				log.debug("pstmt created");
			
				pstmt.setInt(1, e);
				pstmt.setInt(2, view);
			}
			
			//Executing the call to SQL
			log.debug("about to execute Query for userId: "+e);
			ResultSet result=pstmt.executeQuery();
			log.debug("Query executed");
			
			
			//https://howtodoinjava.com/java/date-time/convert-string-to-localdate/			
			//Retrieving the reimbursements from the call to SQL
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
				employeeReimbursement.add(result.getInt("reimb_author"));
				
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


}
