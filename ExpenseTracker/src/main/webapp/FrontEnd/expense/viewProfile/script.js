document.addEventListener("DOMContentLoaded",SendData);
let viewprofile = document.getElementById("viewprofile");
function SendData(event){
	event.preventDefault();
	fetch("http://localhost:8080/ExpenseTracker/expense",{
		method:"POST",
		headers:{
			"Content-Type":"application/x-www-form-urlencoded"
		},
		body:new URLSearchParams({
			action:"viewprofile"
		})	
	})
	.then(response =>response.json())
	.then(data =>{
		console.log(data[0]);
		console.log(viewprofile);
		console.log("hello");
		 viewprofile.innerHTML = `
            <p>Name: ${data[0].name}</p>
            <p>Email: ${data[0].email}</p>
            <p>CreatedDate: ${data[0].createdDate}</p>
            <p>PhoneNumber: ${data[0].phoneNumber}</p>
            <p>Number of Expenses: ${data[0].numberOfExpense}</p>
            <p>Total Amount: ${data[0].totalAmount}</p>
        `;
	})
	.catch(error =>{
		console.log(error);
	});
}