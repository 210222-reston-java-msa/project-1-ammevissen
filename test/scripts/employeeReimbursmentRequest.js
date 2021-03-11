function employeeReimbursementRequest(){

	console.log("Employee Reimbursement Request");

	let amount=document.getElementById('amount').value;

    let travel=document.getElementById('travel').value;
    let education=document.getElementById('education').value;

    let options =document.querySelectorAll('input[name="type"]')
    let type;

    for (const option of options){
        if (option.checked){
            type=option.value;
            break;
        }
    }

    //https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/
    console.log(amount);
    // console.log(travel);
    // console.log(education);
    console.log(type);



}