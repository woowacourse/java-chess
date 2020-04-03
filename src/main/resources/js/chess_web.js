$(document).ready(function () {
    document.getElementById("startBtn").onclick = function () {
        window.location.href = '/start';
    };
    if (window.location.pathname !== "/") {
        document.getElementById("startBtn").innerHTML = ("재시작");
    }
});
