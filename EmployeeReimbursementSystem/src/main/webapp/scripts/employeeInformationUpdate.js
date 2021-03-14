//on page startup 
let SesInfo = sessionStorage.getItem('userId');
console.log(`the current user is: ${SesInfo}`);

let firstName;
let lastName;
let username;
let userId;
let email;
let role;

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
	} else{
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


document.getElementById("firstName").innerHTML=firstName;
document.getElementById("lastName").innerHTML=lastName;
document.getElementById("username").innerHTML=username;
document.getElementById("userId").innerHTML=userId;
document.getElementById("email").innerHTML=email;

console.log(role);
if (Number(role)==1){
    document.getElementById("role").innerHTML="  employee"

}else{
    document.getElementById("role").innerHTML="  manager"
}



function update(){
	console.log("updating employee profile");
	let SesInfo = sessionStorage.getItem('userId');
	let SesInfoOjb = JSON.parse(SesInfo); // parse the data that we see == to that attribute
	e=SesInfoOjb.e;
	
	console.log(SesInfoOjb.userId);
			
	let firstName=e.firstName;
	let lastName=e.lastName;
	let username=e.username;
	let userId=e.userId;
	let email=e.email;
	let role=e.roleId;
	let password=e.password;

	let newFirstName=document.getElementById("newFirstName").value;
	let newLastName=document.getElementById("newLastName").value;
	let newPassword=document.getElementById("newPassword").value;
	let newPasswordRepeat=document.getElementById("newPasswordRepeat").value;
	
	if (newFirstName!=""){
		firstName=newFirstName;
	}
	
	if (newLastName!=""){
		lastName=newLastName;
	}
	
	if (newPassword===newPasswordRepeat && newPassword!=""){
		password=newPassword;
	}
	
	
	let employeeTemplate={
		userId : userId,
		username : username,
		password:password,
		firstName : firstName,
		lastName : lastName,
		email : email,
		roleId : role
		}

	console.log("step 1");
	let xhr=new XMLHttpRequest();

	console.log("step 2");	
	xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('userId', this.responseText);

			console.log("heading to employee information");
            window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeInformation.html";

			console.log(sessionStorage.getItem('userId'));
            
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("could not update user info");

        }
    }

	console.log("step 3");
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/employeeUpdate")

	console.log("step 4");
    // 4. xhr.send();
    xhr.send(JSON.stringify(employeeTemplate))
	console.log("Done");
}

function employeeHome(){
	window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeHome.html";
}

function logout(){

		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/logout");
		xhr.send();
		
		sessionStorage.removeItem('userId');
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/";		
}