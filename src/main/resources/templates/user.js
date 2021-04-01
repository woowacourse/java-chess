let $signup = document.querySelector("#signup");

$signup.addEventListener('click', addUser);

function addUser() {
    location.replace('/adduser');
}

function alertSuccess() {
    alert('회원 추가 성공');
}