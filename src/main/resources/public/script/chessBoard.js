const table = document.getElementById("chessBoard");
for (let i = 0; i < 8; i++) {
  const newTr = document.createElement("tr");
  for (let j = 0; j < 8; j++) {
    const newTd = document.createElement("td");

    const row = String(8 - i); // 열(12345678)
    const asciiNum = 'a'.charCodeAt(); // h의 아스키코드
    const column = String.fromCharCode(asciiNum + j);
    newTd.id = column + row;
    newTr.appendChild(newTd);
  }
  table.appendChild(newTr);
}

const $chessBoard = document.getElementById("chessBoard");
$chessBoard.addEventListener('click', testOutput);

function testOutput(event) {
  if (event.target.className == "a8") {
    alert("find a8")
  }
}