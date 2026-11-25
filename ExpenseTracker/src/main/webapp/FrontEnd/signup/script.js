let signupForm = document.getElementById("signupForm");

signupForm.addEventListener("submit", SendData);


function SendData(event) {

    event.preventDefault();

    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let phoneNumber = document.getElementById("phoneNumber").value;

    	fetch("http://localhost:8080/ExpenseTracker/auth", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            name: name, email: email,
            password: password,
			phoneNumber: phoneNumber,	
			action:"signup"
        })
    })

        .then(response => response.text())

		.then(data => {
		    data = data.trim();
			//console.log("hello");
		    console.log(data);

		    if (data === "success") {
		        window.location.href = "../expense/index.html";
		    }
		    else {
		        alert("Account creation failed!");
		        window.location.href = "../index.html";
		    }
		})
		
        .catch(error => {
            console.log(error);
        });

}