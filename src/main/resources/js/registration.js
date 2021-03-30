const $registration = document.querySelector('input[value=registration]');

$registration.addEventListener('click', register);

function register() {
    const id = document.querySelector('input[name=id]').value;
    const pwd = document.querySelector('input[name=psw]').value;
    if (id !== "" && pwd !== "") {
        const params = {
            userId: id,
            password: pwd,
        };
        const http = new XMLHttpRequest();
        const url = '/registration';

        http.open('POST', url);
        http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
        http.onreadystatechange = function () {
            if (http.readyState === XMLHttpRequest.DONE) {
                if (http.status === 200) {
                    alert('성공적으로 회원가입이 완료되었습니다!')
                    window.location.replace("/")
                } else {
                    alert("이미 존재하는 id 입니다!")
                }
            }
        };
        http.send(JSON.stringify(params));
    }
}