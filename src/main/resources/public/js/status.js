function getStatistics() {
    return new Promise(((resolve, reject) => {
        let roomID = $("#roomID").val();
        $.ajax({
            type: 'get',
            url: '/status/' + roomID,
            dataType: 'json',
            error: function (xhr, status, error) {
                reject();
            },
            success: function (json) {
                resolve(json);
            }
        });
    }))
}

function status() {
    let run = async () => {
        let response = await getStatistics();
        let text = "";
        for (let i = 0; i < 2; i++) {
            text += response[i]["team"] + "팀 점수 : " + response[i]["score"]["amount"] + " 결과 : " + response[i]["gameResult"] + '<br>';
        }
        $('#modal-body').html(text);
        $('#statusModal').modal('show');
    }
    run();
}