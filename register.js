function check() {
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    let confirmpassword = document.getElementById('confirmpassword').value
    let email = document.getElementById('email').value
    let mobile = document.getElementById('mobile').value
    let number=0;
    let capital=0;
    let specialchar=0;
    for (let i= 0; i < password.length; i++) {
        var element = password[i];
        if(element.charCodeAt(0)>=65 && element.charCodeAt(0)<=90){
            capital=1
        }
        if(element.charCodeAt(0)>=58 && element.charCodeAt(0)<=64){
            specialchar=1
        }
        if(element.charCodeAt(0)>=48 && element.charCodeAt(0)<=57){
            number=1
        }
    }
    if(username.length<6){
        alert("username length is not sufficient")
        location.reload()
    }
    else if(password.length<8){
        alert("password length is not sufficient")
        location.reload()
    }
    else if(capital==0){
        alert("Password should have a capital letter")
        location.reload()
    }
    else if(specialchar==0){
        alert("Password should have a special symbol")
        location.reload()
    }
    else if(number==0){
        alert("Password should have a number")
        location.reload()
    }
    else if(password!=confirmpassword){
        alert("Passwords doesn't match")
        location.reload()
    }
    else if(!email.includes("@gmail.com")){
        alert("Enter correct email")
        location.reload()
    }
    else if(!(mobile.charCodeAt(0)>=54 && mobile.charCodeAt(0)<=57)){
        alert("Enter correct number")
        location.reload()
    }
    else if(mobile.length!=10){
        alert("Longer/shorter Number is given")
        location.reload()
    }
    else{
        alert("Registration Successful!")
    }
}
