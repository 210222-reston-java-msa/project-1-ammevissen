let firstName="Andy";
let lastName="Mevissen";
let username="ammevissen";
let userID="4";
let email="ammevissen@email.com";
let role="1";

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
