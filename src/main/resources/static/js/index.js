const FILES = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
const RANKS = ['1', '2', '3', '4', '5', '6', '7', '8'];
const REVERSE_RANKS = RANKS.reverse();

$(document).ready(function(){
    initChessBoard();
    initChessPieces();
    initUsers();
    currentTeam();
    triggerEvents();
});

function initChessBoard() {
    for (let r = 0 ; r < REVERSE_RANKS.length ; r++) {
    addChessBoardRow();
    for (let f = 0; f < FILES.length ; f++) {
            addChessBoardSquare(r, f);
        }
    }
}

function initChessPieces() {
    $.ajax({ 
        url: "/board",
        method: "GET",
        dataType: "json"
        })
    .done(function(json) {
        for(let element of json.board) {
            let team = element['team'];
            let piece = element['piece'];
            let position = element['position'].toUpperCase();
            let pieceImage = team + piece; // blackPawn

            if(team !== 'empty') {
                placeChessPiece(position, pieceImage);
            }
        }
    })
    .fail(function(xhr, status, errorThrown) {
        alert('error !');
    })
}

function initUsers() {
    $.ajax({ 
        url: "/users",
        method: "GET",
        dataType: "json"
    })
    .done(function(json) {

        $('#white-player-id').text(json['whiteUser']['id']);
        $('#white-player-name').text(json['whiteUser']['name']);
        
        $('#black-player-id').text(json['blackUser']['id']);
        $('#black-player-name').text(json['blackUser']['name']);
    })
    .fail(function(xhr, status, errorThrown) {
        alert('error !');
    })
}

function triggerEvents() {
    $("button#move").click(function(){
        movePiece();
    });
}

function addChessBoardRow() {
    $('#chess-board').append('<tr>');
}

function addChessBoardSquare(r, f) {
    const rank = REVERSE_RANKS[r];
    const file = FILES[f];
    const squareId = file + rank;
    const color = getColor(r, f);
    $('#chess-board tr').last().append(`<td id=${squareId} style="background-color: ${color}">`);
}

function getColor(r, f) {
    if(isEven(r, f)) {
        return "#AD8B73";
    }
    return "#CEAB93";
}

function isEven(r, f) {
    return (r + f) % 2 === 0;
}

function placeChessPiece(position, pieceImage) {
    $('#chess-board td#' + position).append(`<img src="images/${pieceImage}.png" />`);
}

function movePiece() {
    $.ajax({ 
        url: "/move",
        data: { 
            from: $('input[name="from"]').val(),
            to: $('input[name="to"]').val()
        },
        method: "POST",
        dataType: "json"
    })
    .done(function(json) {
        clearChessBoard();
        initChessBoard();
        currentTeam();
        for(let element of json.board) {
            let team = element['team'];
            let piece = element['piece'];
            let position = element['position'].toUpperCase();
            let pieceImage = team + piece; // blackPawn

            if(team !== 'empty') {
                placeChessPiece(position, pieceImage);
            }
        }
    })
    .fail(function(xhr, status, errorThrown) {
        alert('error !');
    })
}

function clearChessBoard() {
    $('#chess-board').children().remove();
}

function currentTeam() {
    $.ajax({ 
        url: "/current-team",
        method: "POST",
        dataType: "json"
    })
    .done(function(json) {
        debugger;
        $('#current-team').text(json);
    })
    .fail(function(xhr, status, errorThrown) {
        alert('error !');
    })
}