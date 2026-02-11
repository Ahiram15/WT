function check() {
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    let number=0;
    let capital=0;
    let specialchar=0;
    for (let i = 0; i < password.length; i++) {
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
        alert("Password must have a capital letter")
        location.reload()
    }
    else if(specialchar==0){
        alert("Password must have a special symbol")
        location.reload()
    }
    else if(number==0){
        alert("Password must have a number")
        location.reload()
    }
    else{
        alert("Login Successful!")
    }
}
