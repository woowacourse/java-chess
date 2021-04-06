class CommandExecutor {

  $gameStatus = document.querySelector('.game-status');
  $chatContent = document.getElementById('chat-contents');

  execute(response, board) {
    switch (response.form) {
      case 'PIECES' :
        board.drawBoard(response.data);
        break;
      case 'STATUS' :
        board.updateBoardStatus(response.data);
        const score = board.getScore()
        this.$gameStatus.innerHTML = `<h3> 당신의 팀 컬러 : ${teamColor} </h3> 현재 점수 <br> 화이트 : ${score.whiteTeamScore}   블랙 : ${score.blackTeamScore}`;
        break;
      case 'MOVE' :
        const currentPosition = document.getElementById(response.data.currentPosition);
        const targetPosition = document.getElementById(response.data.targetPosition);
        board.selectItem(currentPosition);
        board.move(targetPosition);
        break;
      case 'COLOR' :
        teamColor = response.data;
        break;
      case 'ERROR' :
        alert(response.data);
        break;
      default :
        this.$chatContent.insertAdjacentHTML('afterbegin', response.userMessage);
    }
  }
}