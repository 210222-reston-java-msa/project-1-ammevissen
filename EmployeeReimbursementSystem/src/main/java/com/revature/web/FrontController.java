package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.repositories.EmployeeDAO;
import com.revature.util.RequestHelper;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(FrontController.class);

	
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FrontController doGet");
		
		
		//Simplifying URI string  
		final String URI = request.getRequestURI().replace("/EmployeeReimbursementSystem/", "");
		
		
		//Selecting the appropriate helper function
		log.debug(URI);
		switch(URI) {
		case "employeeLogin":
			log.debug("heading to process Employee Login");
			RequestHelper.processEmployeeLogin(request, response);
			break;
		case "managerLogin":
			log.debug("heading to process Manager Login");
			RequestHelper.processManagerLogin(request, response);
			break;
//		case "employeeHome":
//			log.debug("heading to Employee Home");
//			RequestHelper.employeeHome(request, response);
//			break;
		case "employeeReimbursement":
			log.debug("heading to process Employee Reimbursement");
			RequestHelper.processEmployeeReimbursement(request, response);
			break;
		case "employeeUpdate":
			log.debug("heading to process Employee Update");
			RequestHelper.processEmployeeUpdate(request, response);
			break;
		case "employeeView":
			log.debug("heading to Employee View");
			RequestHelper.empoloyeeView(request, response);
			break;
		case "logout":
			log.debug("logging out");
			RequestHelper.loggingOut(request, response);
			break;
		case "managerView":
			log.debug("Manager View Reimbursements");
			RequestHelper.managerView(request, response);
			break;
		case "viewEmployees":
			log.debug("View Employees");
			RequestHelper.viewEmployees(request, response);
			break;
		case "approve":
			log.debug("Approving requests");
			RequestHelper.approve(request, response);
			break;
			
				
		}
		System.out.println("doGet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("FrontController doPost");
		doGet(request, response);
	}

}
