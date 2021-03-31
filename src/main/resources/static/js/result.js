function showResult() {
    const xmlHttp = new XMLHttpRequest();
    const url = getBaseUrl() + '/show/result';
    xmlHttp.onreadystatechange = function () {
        if (isValidHttpResponse(this)) {
            const resultDTO = JSON.parse(this.responseText);
            printResult(resultDTO);
        }
    }
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}

function getBaseUrl() {
    return 'http://localhost:8080/chessgame/' + roomId;
}

function isValidHttpResponse(xmlHttp) {
    return xmlHttp.readyState === 4 && xmlHttp.status === 200;
}

function printResult(resultDTO) {
    const blackTeamScore = resultDTO.blackTeamScore;
    const whiteTeamScore = resultDTO.whiteTeamScore;
    document.getElementById('black-team-score').innerText = '흑팀 : ' + blackTeamScore + '점';
    document.getElementById('white-team-score').innerText = '백팀 : ' + whiteTeamScore + '점';
    document.getElementById('winner-team').innerText = '우승팀 : ' + resultDTO.winnerTeamType;
}

function addRestartEvent() {
    const restartButton = document.getElementById('restart-button');
    restartButton.addEventListener('click', function (event) {
        const xmlHttp = new XMLHttpRequest();
        const url = getBaseUrl() + '/restart';
        xmlHttp.onreadystatechange = function () {
            if (isValidHttpResponse(this)) {
                window.location = JSON.parse(this.responseText);
            }
        }
        xmlHttp.open('GET', url, true);
        xmlHttp.send();
    });
}

showResult();
addRestartEvent();
