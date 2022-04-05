function getScore() {
    $.ajax({
        url: "/status",
        type: "GET",
        success: function (data) {
            data = JSON.parse(data);
            document.getElementById("whiteScore").innerText = data["whiteScore"];
            document.getElementById("blackScore").innerText = data["blackScore"];
        },
        error: function (data){
            alert(data);
        }
    })
}