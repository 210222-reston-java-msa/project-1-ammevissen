package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;


import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static Logger log = Logger.getLogger(EmployeeDAO.class);
	
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
	
	
	public static int insertEmployeeReimbursement(Reimbursement reim) {

		try {
			Connection conn=ConnectionUtil.getConnection();
		
			String reimbursment="INSERT INTO ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id)"
								+"VALUES(?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(reimbursment);
			log.debug("pstmt created");
		
			pstmt.setDouble(1, reim.getAmount());
			pstmt.setString(2, reim.getDescription());
			pstmt.setInt(3, reim.getAuthor());
			pstmt.setInt(4, reim.getStatusId());
			pstmt.setInt(5, reim.getTypeId());
		
			return pstmt.executeUpdate();
		}catch (SQLException ex) {
			log.warn("Unable insert reimbursement into the database", ex);
			return 0;
		}
	}
	
	public static int insertEmployeeUpdate(Employee e) {
		try {
			Connection conn=ConnectionUtil.getConnection();
		
			String reimbursment="UPDATE ers_users"
					+ "set ers_password="
					+ "ers_first_name=?"
					+ "ers_last_name=?"
					+ "where ers_users_id =?";

			PreparedStatement pstmt = conn.prepareStatement(reimbursment);
			log.debug("pstmt created");
		
			pstmt.setString(1, e.getPassword());
			pstmt.setString(2, e.getFirstName());
			pstmt.setString(3, e.getLastName());
			pstmt.setInt(4, e.getUserId());
			
			
			return pstmt.executeUpdate();
		}catch (SQLException ex) {
			log.warn("Unable insert reimbursement into the database", ex);
			return 0;
		}
		
		
	}


}
