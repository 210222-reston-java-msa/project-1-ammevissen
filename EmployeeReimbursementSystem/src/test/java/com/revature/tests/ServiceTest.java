package com.revature.tests;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.repositories.ManagerDAOImpl;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;


public class ServiceTest {

	
	private EmployeeService eserv;
	private ManagerService mserv;
	
	private EmployeeDAOImpl eDaoImpl;
	private ManagerDAOImpl mDaoImple;
	
	@Before
	public void setup() {
		
		eserv=new EmployeeService();
		mserv=new ManagerService();
		
		eDaoImpl=mock(EmployeeDAOImpl.class);
		mDaoImple=mock(ManagerDAOImpl.class);
		
		eserv.eDAO=eDaoImpl;
		mserv.mDAO=mDaoImple;
			
	}
	
	
	@Test
	public void happyPathScenarioEmployee1() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 1);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("ammevissen", "p@55w0rd", 1);
		
		assertEquals(e, eFoundByUserName);
	}

	@Test
	public void happyPathScenarioEmployee2() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 2);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("ammevissen", "p@55w0rd", 2);
		
		assertEquals(e, eFoundByUserName);
	}
	
	@Test
	public void happyPathScenarioEmployeeWrongPassword() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 1);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("ammevissen", "password", 1);
		
		assertEquals(null, eFoundByUserName);
	}
	
	@Test
	public void happyPathScenarioEmployeeWrongUsername() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 1);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("smevissen", "p@55w0rd", 1);
		
		assertEquals(null, eFoundByUserName);
	}
	
	@Test
	public void happyPathScenarioManager() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 2);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("ammevissen", "p@55w0rd", 2);
		
		assertEquals(e, eFoundByUserName);
	}

	@Test
	public void happyPathScenarioManagerWrongRole() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 1);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("ammevissen", "p@55w0rd", 2);
		
		assertEquals(null, eFoundByUserName);
	}
	
	@Test
	public void happyPathScenarioManagerWrongPassword() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 2);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("ammevissen", "password", 2);
		
		assertEquals(null, eFoundByUserName);
	}
	
	@Test
	public void happyPathScenarioManagerWrongUsername() {
		Employee e=new Employee(1, "ammevissen", "p@55w0rd", "andy", "mevissen", "ammevissen@email", 2);     
		
		when(eDaoImpl.findByUsername(e.getUsername())).thenReturn(e);
		
		Employee eFoundByUserName=eserv.confirmLogin("smevissen", "p@55w0rd", 2);
		
		assertEquals(null, eFoundByUserName);
	}
}
