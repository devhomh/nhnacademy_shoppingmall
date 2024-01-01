
const SERVER_URL = "http://localhost:8080"

window.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    const validateForm = form => {
        if(form['user_id'].value.trim() === ""){
            alert("아이디를 입력하세요");
            form['user_id'].focus();
            return false;
        }
        if(form['user_password'].value.trim() === ""){
            alert("비밀번호를 입력하세요");
            form['user_password'].focus();
            return false;
        }
        return true;
    }

    const id = document.getElementById("user_id");
    const password = document.getElementById("user_password");

    // loginForm.addEventListener("submit", event => {
    //     event.preventDefault();
    //     if(!validateForm(event.target)){
    //         return;
    //     }
    //     doLogin(id, password);
    // })
})

const doLogin = async (id, password) => {
    const url = SERVER_URL + "/loginAction.co";
    const data = {
        userId : id,
        userPassword : password
    }
    const options = {
        method : "POST",
        header : {
            "content-type" : "application/json"
        },
        body : JSON.stringify(data)
    }

    const response = await fetch(url, options);
}
