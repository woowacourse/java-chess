function getScore() {
    $.ajax({
        url: "/status",
        type: "GET",
        success: function (data) {
            data = JSON.parse(data);
            console.log(typeof data)
            console.log(data);
            console.log(data["whiteScore"])
            document.getElementById("whiteScore").innerText = data["whiteScore"];
            document.getElementById("blackScore").innerText = data["blackScore"];
        },
        error: function (data){
            alert(data);
        }
    })
}