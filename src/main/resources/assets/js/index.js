const templateGame = (id) =>
  `<a href="/game/${id}" class="chess-game"><div class="chess-game-title">체스 게임 ${id}</div></a>`;

const chessGamesElement = document.querySelector('.chess-games');
const chessCreateElement = document.querySelector('.chess-create');

chessCreateElement.onclick = () => {
  fetch('/create', {
    method: 'POST',
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.statusCode == 1) {
        location.replace('/game/' + data.dto);
      }
    });
};

fetch('http://localhost:4567/games')
  .then((response) => response.json())
  .then((data) => drawGameList(data.dto));

function drawGameList(games) {
  games.forEach((id) => (chessGamesElement.innerHTML += templateGame(id)));
}
