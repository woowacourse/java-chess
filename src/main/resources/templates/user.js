let $signup = document.querySelector("#signup");

$signup.addEventListener('click', addUser);

async function addUser() {
    location.replace('/adduser');
}

function alertSuccess() {
    alert('회원 추가 성공');
}

function alertUser() {
    // console.log(document.getElementById("name").onsubmit);
    // if(document.getElementById("name").onsubmit == false)
    // alert('로그인 성공');
}
// async function startGame(event) {
//     console.log(event.target);
//     let data = {
//         name: document.getElementById("login"),
//         password: document.getElementById("signup")
//     }
//     await fetch('/findUser', {
//         method: 'POST',
//         body: JSON.stringify(data),
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     }).then(function (res) {
//         console.log(res);
//         if (res == null) {
//             alert('등록되지 않은 사용자입니다.');
//         } else {
//             fetch('/user', {
//                 method: 'POST',
//                 body: res,
//                 headers: {
//                     'Content-Type': 'application/json'
//                 }
//             }).then(res => res.json());
//         }
//     });
// }
