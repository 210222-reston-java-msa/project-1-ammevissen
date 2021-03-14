function managerHome(){
	window.location = "http://localhost:8080/EmployeeReimbursementSystem/managerHome.html";
}

function logout(){

		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/logout");
		xhr.send();
		
		sessionStorage.removeItem('userId');
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/";		
}


function Reimbursements(){

	console.log("Starting Manager Reimbursement Viewing");
	let SesInfo = sessionStorage.getItem('userId');

	let firstName;
	let lastName;
	let username;
	let userId;
	let email;
	let role;
	let view=Number(document.getElementById('viewChoice').value);
	let employeeId=Number(document.getElementById('userId').value);

	if (SesInfo === null) {
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";
	} else {
		
		let SesInfoOjb = JSON.parse(SesInfo); // parse the data that we see == to that attribute
		e=SesInfoOjb.e;
	
		console.log(SesInfoOjb.userId);
	
		if (e.username!=null){
			firstName=e.firstName;
			lastName=e.lastName;
			username=e.username;
			userId=e.userId;
			email=e.email;
			role=e.roleId;
			//password=e.password;
		}else{
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";
		}
	}

	console.log("user info");
	console.log(firstName);
	console.log(lastName);
	console.log(username);
	console.log(userId);
	console.log(email);
	console.log(role);
	console.log(view);
	console.log(employeeId);

	let managerViewTemplate={
		userId : userId,
		username : username,
		firstName : firstName,
		lastName : lastName,
		email : email,
		roleId : role,
		view:view,
		employeeId: employeeId
	}
	
	
	
	console.log("step 1");
	let xhr=new XMLHttpRequest();

	console.log("step 2");	
	xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");
			
			sessionStorage.setItem('userId', this.responseText)
			
			let SesInfo = sessionStorage.getItem('userId');
			
			console.log(`the current out is: ${SesInfo}`);
			
			let SesInfoOjb = JSON.parse(SesInfo);
			
			reimbursements=SesInfoOjb.list
			console.log(reimbursements);
			
			let table=document.getElementById("viewRequests");
			let tableBody=document.getElementById("body");
			tableBody.remove()
			
			let newTableBody=document.createElement("tbody");
			newTableBody.setAttribute("id", "body");
			table.appendChild(newTableBody);
			
			reimbursements.forEach( (reimbursement) => {
			
				//Create rows:
				let tableRow = document.createElement("tr");
	        	newTableBody.appendChild(tableRow);
				
				reimbursement.forEach((value) => {
				
					let tableData = document.createElement("td");
            		tableRow.appendChild(tableData);
            		tableData.innerHTML = value;
				});			
			});
						
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }

	console.log("step 3");
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/managerView")

	console.log("step 4");
    xhr.send(JSON.stringify(managerViewTemplate))
	console.log("Done");

}
