let gameIndexNum;

let thisGame = {
	round: 0,
	turn: 0,
	turnName:"",
	whiteName:"",
	blackName:""
};

window.onload = function() {
	getIndexGame();
};

function onDrop(source, target, piece, newPos, oldPos, orientation) {
	let result = ""
	if(thisGame.turnName == "black"){
		if (piece.search(/w/) !== -1) {
			return 'snapback';
		}
	}
	if(thisGame.turnName == "white"){
		if (piece.search(/b/) !== -1) {
			return 'snapback';
		}
	}
	_source = positionParser(source);
	_target = positionParser(target);

	$.ajax({
		type: "POST",
		url: '/api/game/move',
		data: {
			"round":thisGame.round,
			"prevX":_source.x,
			"prevY":_source.y,
			"nextX":_target.x,
			"nextY":_target.y
		},async:false,
		success:function(e){
			let data =  jQuery.parseJSON(e)
			if(data.history.canMove == false){
				result = 'snapback'
			}
			if(data.history.isKingDead ==true){
				alert(thisGame.turnName+"이 이겼습니다!");
			}
			turnChange(data.history.turn)

		}
	})
	return result
}

let positionParser = (s)=>{
	let position = {
		x:0,
		y:0
	}
	position.x = s[0].charCodeAt(0) - 97;
	position.y = 8 - s[1];
	return position;
}
var config = {
	draggable: true,
	position: 'start',
	onDrop: onDrop
};


var board1 = Chessboard('board1', config);


var getIndexGame = function() {
	axios
		.get('/api/history/')
		.then(function(response) {
			var html = '';
			var data = response.data.gameList;
			for (var i = 0; i < data.length; i++) {
				html +=
					'<option value="' +
					data[i].round +
					'">' +
					data[i].round +
					' : ' +
					data[i].whitePlayer +
					' vs ' +
					data[i].blackPlayer +
					'</option>';
			}
			$('select[name="selectGame"]').append(html);
		})
		.catch();
};

var loadGame = function() {
	var round = document.getElementById('selectGame').value;
	axios.get('/api/history/' + round).then(function(response) {
		let data = response.data.history;
		let loadedArrange = rowToBoardParser(data.rows);
		drawBoard(loadedArrange);
		thisGame.turn = data.turn;
		thisGame.round = data.round;
		turnChange(data.turn);
		turnChange(data.turn);
		$("#whiteName").text("white: "+thisGame.whiteName)
		$("#blackName").text("black: "+thisGame.blackName)

	});
};

let turnChange = (turn) => {
	$('#total-turn').text(turn);
	if (turn % 2 == 1) {
		thisGame.turnName = "white"
	} else {
		thisGame.turnName = "black"
	}
	$('#turn').text(thisGame.turnName);

};

var newGame = function() {
	  thisGame.whiteName = document.getElementById('white-name-input').value
	  
	  thisGame.blackName = document.getElementById('black-name-input').value
	axios({
		method: 'post',
		url: '/api/game/new',
		data: {
			whiteName: thisGame.whiteName,
			blackName:  thisGame.blackName
		}
	}).then(function(response) {
		let data = response.data.history;
		let loadedArrange = rowToBoardParser(data.rows);
		thisGame.turn = data.turn;
		thisGame.round = data.round;
		drawBoard(loadedArrange);
		turnChange(data.turn);
		$("#whiteName").text("white: "+thisGame.whiteName)
		$("#blackName").text("black: "+thisGame.blackName)

	});
};

let stringToRowParser = (s) => {
	let boardRow = '';
	var numberDot = 0;
	for (var i = 0; i < s.length; i++) {
		if (s[i] == '.') {
			numberDot++;
		} else if (numberDot != 0) {
			boardRow += numberDot;
			numberDot = 0;
			boardRow += s[i];
		} else {
			boardRow += s[i];
		}
	}
	if (numberDot != 0) {
		boardRow += numberDot;
	}

	return boardRow;
};

let rowToBoardParser = (row) => {
	let boardArrange = '';
	var rowBoard = [];
	for (var i = 0; i < 8; i++) {
		rowBoard.push(stringToRowParser(row[i]));
	}
	boardArrange = rowBoard.join('/');
	return boardArrange;
};

let drawBoard = (e) => {
	$('#board-container').show();
	$('.modal').hide();
	board1.position(e);
};

let getScore =() =>{
	axios({
		method: 'get',
		url: '/api/game/score/'+thisGame.round
	}).then(function(response) {
		$('#score').show();
		$('#whiteScore').text(response.data.result.whiteScore);
		$('#blackScore').text(response.data.result.blackScore);
		setTimeout(function(){
			$('#score').hide();
		},2000)

	});
}