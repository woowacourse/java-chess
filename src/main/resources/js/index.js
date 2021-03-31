// const $new = document.querySelector('input[value=new]')
// const $id = document.querySelector('input[name=id]').value;

// $new.addEventListener('click', newGame);

// function newGame() {
//     if ($id !== "") {
//         const params = {
//             userId: $id,
//         };
//         const http = new XMLHttpRequest();
//         const url = '/game/new' + $id;
//
//         http.open('POST', url);
//         http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
//         http.onreadystatechange = function () {
//             if (http.readyState === XMLHttpRequest.DONE) {
//                 if (http.status === 200) {
//                     // cookie session
//                     alert($id + "님 환영합니다!")
//                     window.location.replace("/menu")
//                 } else {
//                     alert("가입해라")
//                     window.location.replace("/")
//                 }
//             }
//         };
//         http.send(JSON.stringify(params));
//     }
// }

// const $login = document.querySelector('input[value=login]');
//
// $login.addEventListener('click', onClickLogin);
//
// function onClickLogin() {
//     const id = document.querySelector('input[name=id]').value;
//     const pwd = document.querySelector('input[name=pwd]').value;
//     if (id !== "" && pwd !== "") {
//         const params = {
//             userId: id,
//             password: pwd,
//         };
//         const http = new XMLHttpRequest();
//         const url = '/menu';
//
//         http.open('POST', url);
//         http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
//         http.onreadystatechange = function () {
//             if (http.readyState === XMLHttpRequest.DONE) {
//                 if (http.status === 200) {
//                     // cookie session
//                     alert(id + "님 환영합니다!")
//                     window.location.replace("/menu")
//                 } else {
//                     alert("가입해라")
//                     window.location.replace("/")
//                 }
//             }
//         };
//         http.send(JSON.stringify(params));
//     }
// }