package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginInfo;
import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;

public class RequestHelper {


	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	
	public static void processEmployeeLogin(HttpServletRequest req, HttpServletResponse res) throws IOException { 
		
		String body=RequestHelperUtil.processEmployeeLogin(req);
		
		LoginInfo loginAttempt=om.readValue(body, LoginInfo.class);
		
		String username=loginAttempt.getUsername();
		String password=loginAttempt.getPassword();
		int role=loginAttempt.getRole();
		
		log.info("User attempted to login with username: "+username);
		Employee e=EmployeeService.confirmLogin(username, password, role);

		if (e!=null) {
			
			
			HttpSession ses=req.getSession();
			ses.setAttribute("userId", e.getUserId());
//			ses.setAttribute("username", e.getUsername());
//			ses.setAttribute("firstName", e.getFirstName());
//			ses.setAttribute("lastName", e.getLastName());
//			ses.setAttribute("email", e.getEmail());
//			ses.setAttribute("roleId", e.getRoleId());
			res.setStatus(200);
			
			PrintWriter pw=res.getWriter();
			res.setContentType("application/json");
			
			pw.println(om.writeValueAsString(e));
		
			res.setStatus(200);
			log.info(username+ " has successfully logged in");
			
		}else {
			res.setStatus(204);
		}
		
	}
	
	
	public static void processManagerLogin(HttpServletRequest req, HttpServletResponse res) throws IOException { 
		
//		BufferedReader reader=req.getReader();
//		StringBuilder s=new StringBuilder();
//		
//		//looping through request data until all data is read into StringBuilder s
//		String line=reader.readLine();
//		while (line!=null) {
//			s.append(line);
//			line=reader.readLine();
//		}
//		
//		//converting StringBuilder s to String
//		String body=s.toString();
//		log.info(body);

		String body=RequestHelperUtil.processEmployeeLogin(req);
		
		LoginInfo loginAttempt=om.readValue(body, LoginInfo.class);
		
		String username=loginAttempt.getUsername();
		String password=loginAttempt.getPassword();
		int role=loginAttempt.getRole();
		
		log.info("User attempted to login with username: "+username);
		Employee e=EmployeeService.confirmLogin(username, password, role);

		if (e!=null) {
			HttpSession ses=req.getSession();
			ses.setAttribute("userId", e.getUserId());
//			ses.setAttribute("username", e.getUsername());
//			ses.setAttribute("firstName", e.getFirstName());
//			ses.setAttribute("lastName", e.getLastName());
//			ses.setAttribute("email", e.getEmail());
//			ses.setAttribute("roleId", e.getRoleId());
			res.setStatus(200);
			
			PrintWriter pw=res.getWriter();
			res.setContentType("application/json");
			
			pw.println(om.writeValueAsString(e));
			log.info(username+ " has successfully logged in");
		}else {
			res.setStatus(204);
		}
	}
	
	public static void processEmployeeReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement info: "+body);
		Reimbursement reim=om.readValue(body, Reimbursement.class);
				
		log.debug("Employee Reimbursement class info: "+reim.toString());
		int result=EmployeeService.processEmployeeReimbursement(reim);
		
		log.debug("the result of adding to the DB"+result);
	}
	
	public static void employeeHome(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement info: "+body);
		
		
		
	}

	
	public static void processEmployeeUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement info: "+body);
		
		Employee e=om.readValue(body, Employee.class);
		
		int result=EmployeeService.processEmployeeUpdate(e);
		
		
		
	}
	
}
