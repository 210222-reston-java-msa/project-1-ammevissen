window.onload = onLoad();


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

function managerViewTemplateFunction(){
	console.log("Starting Manager Reimbursement Viewing");
	let SesInfo = sessionStorage.getItem('userId');

	let firstName;
	let lastName;
	let username;
	let userId;
	let email;
	let role;
	let view=1;
	let employeeId=0;

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
	
	return(managerViewTemplate);
}

function onLoad(){


	let managerViewTemplate=managerViewTemplateFunction();
	
	
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
			
			if (reimbursements.length>0){
				let i=2
				reimbursements.forEach( (reimbursement) => {
			
					//Create rows:
					let tableRow = document.createElement("tr");
	        		newTableBody.appendChild(tableRow);
				
					reimbursement.forEach((value) => {
					
						let tableData = document.createElement("td");
            			tableData.innerHTML = value;
            			tableData.value = value;
            			tableRow.appendChild(tableData);
					});
				
					 // https://stackoverflow.com/questions/17730621/how-to-dynamically-add-options-to-an-existing-select-in-vanilla-javascript
    	        	//https://www.javascripttutorial.net/javascript-dom/javascript-add-remove-options/#:~:text=%20To%20add%20an%20option%20dynamically%20to%20a,the%20option%20to%20the%20select%20box.%20More%20
            
				
					let tableData = document.createElement("td");
            	
            	    let mySelect=document.createElement("select");
                	let myOption=document.createElement("Option");
                	myOption.text="Pending";
                	myOption.value=1;
                	mySelect.appendChild(myOption);
    
                	myOption=document.createElement("Option");
                	myOption.text="Approve";
                	myOption.value=2;
                	mySelect.appendChild(myOption);
    
                	myOption=document.createElement("Option");
                	myOption.text="Reject";
                	myOption.value=3;
                	mySelect.appendChild(myOption);
                	mySelect.setAttribute("id",i);
                	tableData.appendChild(mySelect);
                	tableRow.appendChild(tableData);
                	i++;
				});
						
        	}
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


function approve(){

	let managerViewTemplate=managerViewTemplateFunction();

	let approveTemplate={
		userId : managerViewTemplate.userId,
		username : managerViewTemplate.username,
		firstName : managerViewTemplate.firstName,
		lastName : managerViewTemplate.lastName,
		email : managerViewTemplate.email,
		roleId : managerViewTemplate.role,
		reimId : 0,
		approve : 1
	}


	var table = document.getElementById("viewRequests");
    let rows=table.rows;

    for (let i=2; i<rows.length; i++){
        let cells=rows[i].cells;
        console.log("i:"+i);
        //console.log(cells[2]);
        approveTemplate.reimId=cells[0].value;
        approveTemplate.approve=Number(document.getElementById(i).value);
        
        
        console.log(`reimId: ${approveTemplate.reimId}`);
        console.log(`approve: ${approveTemplate.approve}`);

		if (approveTemplate.reimId>1){
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
				}
			}	

			console.log("step 3");
		    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/approve")

			console.log(`reimID: ${approveTemplate.reimId}  approval status: ${approveTemplate.approve}`)
				
			console.log("step 4");
    		xhr.send(JSON.stringify(approveTemplate));
			console.log(`Done with ${i}`);
    	}

	}


//update the page()
onLoad();
}