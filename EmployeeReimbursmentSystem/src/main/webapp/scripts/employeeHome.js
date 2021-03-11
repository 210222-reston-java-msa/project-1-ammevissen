function employeeChoice(){

	console.log("get task choice");
	let choice=document.getElementById('task').value;
	
	console.log(`employee task is ${task}`);

	let userID = sessionStorage.getItem('userId');

	console.log(`the current user is: ${userID}`);

	
	switch (choice){
		case "submit a reimbursement request":
			window.location = "http://localhost:8080/EmployeeReimbursmentSystem/employeeReimbursmentRequest.html";
			break;
		case "view personal information":
			window.location = "http://localhost:8080/EmployeeReimbursmentSystem/employeeInformation.html";
			break;
		case "view requests":
			window.location = "http://localhost:8080/EmployeeReimbursmentSystem/employeeView.html";
			break;		
	}	
}