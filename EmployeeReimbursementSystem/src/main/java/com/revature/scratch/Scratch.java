package com.revature.scratch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;

public class Scratch {

	private static Logger log = Logger.getLogger(Scratch.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		ObjectMapper objectMapper=new ObjectMapper();
//		JacksonTestModel test1=new JacksonTestModel("yellow", "renault");
//		try {
//			objectMapper.writeValue(new File("target/car.jason"), test1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
//		try {
//			JacksonTestModel car = objectMapper.readValue(json, JacksonTestModel.class);
//			System.out.println(car);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Model 1");
//
//			//e.printStackTrace();
//		}
//		
//		//model names and JSON object must be the same
//		try {
//			JacksonTestModel2 car = objectMapper.readValue(json, JacksonTestModel2.class);
//			System.out.println(car);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Model 2");
//			//e.printStackTrace();
//		}
		
		
//		String username="ammevissen";
//		String password="p@55w0rd";
//		int role=1;
//				
//		log.info("User attempted to login with username: "+username);
//		Employee e=EmployeeService.confirmLogin(username, password, role);
//
//		if (e!=null) {
//			
//			log.info(username+ " has successfully logged in");
//			
//		}else {
//			log.info(username+ " has not successfully logged in");
//		}
		
		
//		String pass="w";
//		String first="andrew";
//		String last="mev";
//		int user=5;
//		
//		try {
//			Connection conn=ConnectionUtil.getConnection();
//		
//			String reimbursement="UPDATE ers_users "
//					+ "SET ers_password= ?, "
//					+ "ers_first_name= ?, "
//					+ "ers_last_name= ? "
//					+ "WHERE ers_users_id = ?";
//
//			PreparedStatement pstmt = conn.prepareStatement(reimbursement);
//			log.debug("pstmt created");
//		
//			pstmt.setString(1, pass);
//			pstmt.setString(2, first);
//			pstmt.setString(3, last);
//			pstmt.setInt(4, user);
//			
//			log.debug(pstmt);
//			log.debug(pstmt.executeUpdate());
//			
//		}catch (SQLException ex) {
//			log.warn("Unable insert reimbursement into the database", ex);
//			log.debug(0);
//		}
		
		
		List<Object> employeeReimbursements= new ArrayList<Object>();		
		int e=5;
		int view=0;   //all
		//int view=1; //pending
		//int view=2; //approved
		//int view=3; //rejected
		
		
		try {
			Connection conn=ConnectionUtil.getConnection();
		
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
			
			ResultSet result=pstmt.executeQuery();
			log.debug("Query executed");
			
			while(result.next()) {
				List<Object> employeeReimbursement= new ArrayList<Object>();
				employeeReimbursement.add(result.getInt("reimb_id"));
				employeeReimbursement.add(result.getDouble("reimb_amount"));
				employeeReimbursement.add(result.getObject("reimb_submitted", LocalDate.class));
				employeeReimbursement.add(result.getObject("reimb_resolved", LocalDate.class));
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
		
	}

}

