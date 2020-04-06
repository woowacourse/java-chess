$(document).ready(function () {
    if (window.location.pathname !== "/") {
        document.getElementById("startBtn").innerHTML = ("재시작");
    }
    if (window.location.pathname === "/") {
        document.getElementById("moveBtn").setAttribute("disabled", "disabled");
    }
    document.getElementById("startBtn").onclick = function () {
        window.location.href = '/start';
    };
    document.getElementById("resumeBtn").onclick = function () {
        window.location.href = '/resume';
    };
});
