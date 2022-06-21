var jwt = localStorage.getItem("jwt");
if (jwt != null) {
    window.location.href = 'login.html'
}

function login() {
    const username = document.getElementById("login").value;
    const password = document.getElementById("pass").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "@{api/auth/signin}");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "login": username,
        "pass": password
    }));
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            const objects = JSON.parse(this.responseText);
            console.log(objects);
            if (objects['status'] === 'ok') {
                localStorage.setItem("jwt", objects['accessToken']);
                Swal.fire({
                    text: objects['message'],
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = 'index.html';
                    }
                });
            } else {
                Swal.fire({
                    text: objects['message'],
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        }
    };
    return false;
}