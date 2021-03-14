package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.ApproveRequestTemplate;
import com.revature.models.Employee;
import com.revature.models.ErrorMsg;
import com.revature.models.LoginInfo;
import com.revature.models.ManagerViewTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.Response;
import com.revature.models.ViewTemplate;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;

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
			res.setStatus(200);
			
			ErrorMsg err=new ErrorMsg("Everything is fine");
			
			List<Object> list=new ArrayList<Object>();
			List<Object> list2=new ArrayList<Object>();
			list.add(11);
			list.add(22);
			list2.add("some List");
			list2.add(5);
			list.add(list2);
			
			
			PrintWriter pw=res.getWriter();
			res.setContentType("application/json");
			
			//pw.println(om.writeValueAsString(e));
			
			Response r=new Response();
			r.setE(e);
			r.setErr(err);
			r.setList(list);
			
			String bothJson=new Gson().toJson(r);
			//String json1=new Gson().toJson(e);
			//String json2=new Gson().toJson(err);
			//String bothJson = "{"+json1+","+json2+"}";
			//String bothJson = "{employee:"+json1+","+"err:"+json2+"}";
			
			//String bothJson = "{employee:"+json1+","+"err:"+json2+"}";
			
			//String bothJson = new Gson().toJson("{employee:"+json1+","+"err:"+json2+"}");
			//String bothJson = "["+json1+","+json2+"]";
			log.debug(bothJson); 
//			
			pw.println(bothJson);
			
			//pw.println(om.writeValueAsString(r));
			
			//pw.println(om.writeValueAsString(r));
			//pw.println(om.writeValueAsString(err));
			
			//pw.println(om.writeValueAsString();
			
			
			//pw.println(om.writeValueAsString({{"employee": e}, {"err": err}}));
		
			res.setStatus(200);
			log.info(username+ " has successfully logged in");
			
		}else {
			res.setStatus(204);
		}
		
	}
	
	
	public static void processManagerLogin(HttpServletRequest req, HttpServletResponse res) throws IOException { 

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
			res.setStatus(200);
			
			ErrorMsg err=new ErrorMsg("None");

			PrintWriter pw=res.getWriter();
			res.setContentType("application/json");
			
			Response r=new Response();
			r.setE(e);
			r.setErr(err);
			
			String bothJson=new Gson().toJson(r);
			
			log.debug(bothJson); 
			
			pw.println(bothJson);

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
		
		PrintWriter pw=res.getWriter();
		res.setContentType("application/json");
		
		Response r=new Response();;
		
		
		if (result==1) {
			r.setE(e);
			r.setErr(new ErrorMsg("None"));
			String bothJson=new Gson().toJson(r);
			log.debug(bothJson); 
			pw.println(bothJson);
		}
		
		
		res.setStatus(200);
	}
	
	public static void loggingOut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession session=req.getSession(false); //I'm capturing the session, but if there isn't one, I don't create a new one.
	
		log.info("Processing logout");
	
		if (session!=null) {
			String username=(String) session.getAttribute("userId");
			log.info(username + " has logged out");
			session.invalidate();
		}
	
		res.setStatus(200);
		
	}
	
	public static void empoloyeeView(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement info: "+body);
		
		ViewTemplate view=om.readValue(body, ViewTemplate.class);
		
		Employee e=new Employee(view.getUserId(), view.getUsername(), view.getFirstName(), view.getLastName(), view.getEmail(), view.getRoleId());
		
		PrintWriter pw=res.getWriter();
		res.setContentType("application/json");
		
		Response r=new Response();;
		r.setE(e);
		r.setList(EmployeeService.employeeView(e.getUserId(), view.getView()));
		
		if (r.getList().size()>0) {
			r.setErr(new ErrorMsg("None"));
			res.setStatus(200);
		}else {
			r.setErr(new ErrorMsg("Fail"));
			res.setStatus(204);
		}

		String bothJson=new Gson().toJson(r);
		log.debug(bothJson); 
		pw.println(bothJson);
		
	}
	
	public static void managerView(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement info: "+body);
		
		ManagerViewTemplate view=om.readValue(body, ManagerViewTemplate.class);
		
		Employee e=new Employee(view.getUserId(), view.getUsername(), view.getFirstName(), view.getLastName(), view.getEmail(), view.getRoleId());
		
		PrintWriter pw=res.getWriter();
		res.setContentType("application/json");
		
		Response r=new Response();;
		r.setE(e);
		if (view.getEmployeeId()==0) {
			r.setList(ManagerService.managerView(view.getView()));			
		}else {
			r.setList(ManagerService.managerView(view.getEmployeeId(), view.getView()));
		}
		
		if (r.getList().size()>0) {
			r.setErr(new ErrorMsg("None"));
			res.setStatus(200);
		}else {
			r.setErr(new ErrorMsg("Fail"));
			res.setStatus(204);
		}

		String bothJson=new Gson().toJson(r);
		log.debug(bothJson); 
		pw.println(bothJson);
		
	}
	
	
	public static void viewEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement info: "+body);
		
		Employee e=om.readValue(body, Employee.class);
		
		PrintWriter pw=res.getWriter();
		res.setContentType("application/json");
		
		Response r=new Response();;
		r.setE(e);
		r.setList(ManagerService.viewEmployees());
		
		if (r.getList().size()>0) {
			r.setErr(new ErrorMsg("None"));
			res.setStatus(200);
		}else {
			r.setErr(new ErrorMsg("Fail"));
			res.setStatus(204);
		}

		String bothJson=new Gson().toJson(r);
		log.debug(bothJson); 
		pw.println(bothJson);
		
	}

	
	public static void approve(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String body=RequestHelperUtil.processEmployeeLogin(req);
		log.debug("Employee Reimbursement Approval info: "+body);
		
		ApproveRequestTemplate approve=om.readValue(body, ApproveRequestTemplate.class);
		
		Employee e=new Employee(approve.getUserId(), approve.getUsername(), approve.getFirstName(), approve.getLastName(), approve.getEmail(), approve.getRoleId());
		
		
		PrintWriter pw=res.getWriter();
		res.setContentType("application/json");
		
		Response r=new Response();;
		r.setE(e);
		if (ManagerService.approve(approve.getApprove(), approve.getUserId(), approve.getReimId())>0) {
			r.setErr(new ErrorMsg("None"));
			res.setStatus(200);
		}else {
			r.setErr(new ErrorMsg("Fail"));
			res.setStatus(204);
		}

		String bothJson=new Gson().toJson(r);
		log.debug(bothJson); 
		pw.println(bothJson);
		
	}
	
	
}