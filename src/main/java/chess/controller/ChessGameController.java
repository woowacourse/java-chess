package chess.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.*;
import chess.domain.piece.Piece;
import chess.exception.GameOverException;
import chess.service.ChessGameService;
import chess.view.OutputViewForWeb;
import spark.Request;
import spark.Response;

public class ChessGameController {
	private final static ChessGameService chessGameService = new ChessGameService();

	public String generateInitialChessGame() throws SQLException {
		Map<String, Object> model = new HashMap<>();
		ChessBoard chessBoard = ChessPiece.generateChessBoard(new ChessInitialPosition());
		ChessGame chessGame = new ChessGame(chessBoard);
		int roomNumber = chessGameService.saveInitialChessGame(chessGame);
		return transmitChessBoardInfo(model, roomNumber, chessGame);
	}

	public String getRoomNumbers() throws SQLException {
		Map<String, Object> model = new HashMap<>();
		model.put("roomNumbers", chessGameService.getRoomNumbers());
		return OutputViewForWeb.render(model, "/select_game.html");
	}

	public String loadProgressingChessGame(Request req) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		int roomNumber = Integer.parseInt(req.queryParams("room-number"));

		Player turn = chessGameService.loadTurn(roomNumber);
		ChessBoard chessBoard = new ChessBoard();
		List<Piece> pieces = chessGameService.loadChessPieces(roomNumber);
		for (Piece piece : pieces) {
			chessBoard.addPiece(piece);
		}
		ChessGame chessGame = new ChessGame(turn, chessBoard);

		return transmitChessBoardInfo(model, roomNumber, chessGame);
	}

	public String movePiece(Request req) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		int roomNumber = Integer.parseInt(req.queryParams("room-number"));
		ChessGame chessGame = generateCurrentChessGame(roomNumber);

		int startX = Integer.parseInt(req.queryParams("start-x"));
		int startY = Integer.parseInt(req.queryParams("start-y"));
		int endX = Integer.parseInt(req.queryParams("end-x"));
		int endY = Integer.parseInt(req.queryParams("end-y"));
		try {
			chessGame.move(Position.getPosition(startX, startY), Position.getPosition(endX, endY));
		} catch (GameOverException e) {
			chessGameService.gameOver(roomNumber);
			model.put("message", e.getMessage());
			return OutputViewForWeb.render(model, "/gameover.html");
		}
		chessGameService.saveChessGame(roomNumber, chessGame);
		return transmitChessBoardInfo(model, roomNumber, chessGame);
	}

	public String getStatusScore(Request req) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		int roomNumber = Integer.parseInt(req.queryParams("room-number"));
		ChessGame chessGame = generateCurrentChessGame(roomNumber);

		model.put("winner", "승자 : " + chessGame.findWinner().name());
		model.put("whiteScore", "백 점수 : " + chessGame.getPlayerScore(Player.WHITE).getScore());
		model.put("blackScore", "흑 점수 : " + chessGame.getPlayerScore(Player.BLACK).getScore());

		return transmitChessBoardInfo(model, roomNumber, chessGame);
	}

	private ChessGame generateCurrentChessGame(int roomNumber) throws SQLException {
		Player turn = chessGameService.loadTurn(roomNumber);
		return new ChessGame(turn, generateChessBoard(roomNumber));
	}

	private ChessBoard generateChessBoard(int roomNumber) throws SQLException {
		ChessBoard chessBoard = new ChessBoard();
		List<Piece> pieces = chessGameService.loadChessPieces(roomNumber);
		for (Piece piece : pieces) {
			chessBoard.addPiece(piece);
		}
		return chessBoard;
	}

	public String transmitChessBoardInfo(Map<String, Object> model, int roomNumber, ChessGame chessGame) {
		model.put("turn", chessGame.getCurrentPlayer().name());
		model.put("roomNumber", roomNumber);
		model.put("board", chessGameService.getPieceImages(chessGame));
		return OutputViewForWeb.render(model, "/chess.html");
	}

	public void handleException(Response response, Exception exception) {
		Map<String, Object> model = new HashMap<>();
		model.put("message", exception.getMessage());
		response.body(OutputViewForWeb.render(model, "error.html"));

	}
}
