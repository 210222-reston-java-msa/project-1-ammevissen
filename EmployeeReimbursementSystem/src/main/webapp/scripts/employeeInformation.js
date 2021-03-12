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
	window.location = window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";;
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
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeInformationUpdate.html";
}