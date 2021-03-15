function managerChoice(){

	//Getting user choice
	console.log("get task choice");
	let choice=document.getElementById('task').value;	
	console.log(`manager task ${task}`);
	
	let userID = sessionStorage.getItem('userId');
	console.log(`the current user is: ${userID}`);
	
	//Going to page user picked
	switch (choice){
		case "approve reimbursements":
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

//Logging out
function logout(){

		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/logout");
		xhr.send();
		
		sessionStorage.removeItem('userId');
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/";		
}