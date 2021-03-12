//on page startup 
let SesInfo = sessionStorage.getItem('userId');

let firstName;
let lastName;
let username;
let userID;
let email;
let role;

if (SesInfo === null) {
	window.location = window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";;
} else {
		
	let SesInfoOjb = JSON.parse(SesInfo); // parse the data that we see == to that attribute
		
	console.log(SesInfoOjb.userId);
		
	if (SesInfoOjb != null) {
		firstName=SesInfoOjb.firstName;
		lastName=SesInfoOjb.lastName;
		username=SesInfoOjb.username;
		userID=SesInfoOjb.userId;
		email=SesInfoOjb.email;
		role=SesInfoOjb.role;
	} else {
		window.location = window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";
	}
}

document.getElementById("firstName").innerHTML=firstName
document.getElementById("lastName").innerHTML=lastName
document.getElementById("username").innerHTML=username
document.getElementById("userID").innerHTML=userID
document.getElementById("email").innerHTML=email
if (role=="1"){
    document.getElementById("role").innerHTML="  employee"

}else{
    document.getElementById("role").innerHTML="  manager"
}




function update(){
	console.log("updating employee profile");
	let SesInfo = sessionStorage.getItem('userId');
	let SesInfoOjb = JSON.parse(SesInfo); // parse the data that we see == to that attribute
	
	
	let firstName=SesInfoOjb.firstName;
	let lastName=SesInfoOjb.lastName;
	let username=SesInfoOjb.username;
	let userID=SesInfoOjb.userId;
	let email=SesInfoOjb.email;
	let role=SesInfoOjb.role;
	let password=SesInfoOjb.password;

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

			console.log("heading to employee Home");
            window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeInformation.html";

            console.log(sessionStorage.getItem('userId'));
        }

        if (this.readyState === 4 && this.status === 204) {

            console.log("failed to update user profile");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect";
        }
    }

	console.log("step 3");
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/employeeUpdate");

	console.log("step 4");
    // 4. xhr.send();
    xhr.send(JSON.stringify(employeeTemplate));
	console.log("Done");
}