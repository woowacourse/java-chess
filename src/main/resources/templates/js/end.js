$.ajax({
    type: 'get',
    url: '/end',
    dataType: 'json',
    error: function (res) {
        alert(res.responseText);
    },
    success: function (response) {
        let scores = response["result"]["scores"];

        let whiteScore, blackScore;
        scores.forEach(function (item) {
            if (item.color == "WHITE") {
                whiteScore = item.score;
            }
            if (item.color == "BLACK") {
                blackScore = item.score;
            }
        });

        let winners="";
        if (response.result.isDraw) {
            winners = "무승부입니다.";
        }

        if (!response.result.isDraw) {
            winners += "우승자: ";
            winners += response.result.winner;
        }
        $("#result-span").text("whiteScore: "+whiteScore+"\n"+"blackScore: "+blackScore+"\n"+winners);
        //alert("whiteScore: "+whiteScore+"\n"+"blackScore: "+blackScore+"\n"+winners);
    }
});