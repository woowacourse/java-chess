let flag = false;
let from = 0;
let to = 0;

const touchA = function (id) {
    if (flag === false) {
        from = id;
        flag = true;
    } else {
        to = id;
        ajaxRequest(from, to);
        flag = false;
    }
};

const setBoard = (black, white) => {
    const blackTeam = black;
    console.log(blackTeam);
    for (key in blackTeam) {
        const spot = blackTeam[key].Spot;
        const piece = blackTeam[key].Piece;
        setPiece(spot, "black" + piece);
    }

    const whiteTeam = white;
    console.log(whiteTeam);
    for (key in whiteTeam) {
        const spot = whiteTeam[key].Spot;
        const piece = whiteTeam[key].Piece;
        setPiece(spot, "white" + piece);
    }
};

const initChess = () => {
    const pieces = document.getElementsByClassName("chessPiece");
    const len = pieces.length;
    for (let i = 0; i < len; i++) {
        const selectedPiece = pieces[i];
        if (selectedPiece.hasChildNodes()) {
            selectedPiece.removeChild(selectedPiece.childNodes[0]);
        }
    }
};

const setPiece = (pos, name) => {
    const pieces = document.getElementsByClassName("chessPiece");
    const selectedPiece = document.getElementById(pos);

    const im2 = document.createElement("img");
    im2.src = `./source/${name}.png`;
    im2.width = 80;
    im2.height = 80;
    selectedPiece.append(im2);
};

const ajaxRequest = (from, to) => {
    console.log("from :" + from + " t o : " + to);
    const xhttp = new XMLHttpRequest();
    const url = "http://localhost:4567/move";
    const query = `?from=${from}&to=${to}`;
    xhttp.addEventListener("load", () => {
        initChess();
        const res = xhttp.response;
        console.log(res);
        const jsonRes = JSON.parse(res);
        const blackTeamArray = jsonRes.Board[0].BLACK;
        console.log(blackTeamArray);
        const whiteTeamArray = jsonRes.Board[1].WHITE;
        console.log(whiteTeamArray);
        setBoard(blackTeamArray, whiteTeamArray);
    });
    xhttp.onreadystatechange = () => {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", url + query, true);
    xhttp.send();
};
