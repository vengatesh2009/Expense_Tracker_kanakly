let signinform = document.getElementById("signinForm");
signinform.addEventListener("submit",SendData);

function SendData(event){

    event.preventDefault();

    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

   fetch("http://localhost:8080/ExpenseTracker/auth", {
        method:"POST",
        headers:{
             "Content-Type": "application/x-www-form-urlencoded"
        },
		
        body:new URLSearchParams({
            email:email,
            password:password,
			action:"signin"
        })
    })
	
    .then(response => response.text())
	.then(data => {
	data = data.trim();
        console.log(data);
		
		if (data === "success") {
		    window.location.href = "../expense/index.html";
		}
		else {
		    alert("Login failed! Please try again.");
		    window.location.href = "../index.html";
		}
		
    })
    .catch(error =>{
        console.log(error);
    });

}