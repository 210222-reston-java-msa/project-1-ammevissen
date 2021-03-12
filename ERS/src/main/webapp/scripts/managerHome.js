function managerChoice(){

	console.log("get task choice");
	let choice=document.getElementById('task').value;
	
	console.log(`manager task ${task}`);
	
	let userID = sessionStorage.getItem('userId');

	console.log(`the current user is: ${userID}`);
	
	
	switch (choice){
		case "approve reimbursement":
			console.log("choice 1");
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/managerApprove.html";
			break;
		case "view employees":
			console.log("choice 2");
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/managerEmployees.html";
			break;
		case "view reimbursement requests":
		console.log("choice 3");
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/managerView.html";
			break;	
	}
}