package chess;

import chess.domain.*;
import chess.domain.ChessInitialPosition;
import chess.domain.piece.Piece;
import chess.exception.GameOverException;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {
	public static void main(String[] args) {
		staticFiles.location("/static");
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "/index.html");
		});

		get("/new", (req, res) -> {
			Map<String, Object> model = new HashMap<>();

			ChessBoard chessBoard = ChessPiece.generateChessBoard(new ChessInitialPosition());
			ChessGame chessGame = new ChessGame(chessBoard);
			int roomNumber = ChessGameService.saveInitialChessGame(chessGame);
			model.put("turn", chessGame.getCurrentPlayer().name());
			model.put("roomNumber", roomNumber);
			model.put("board", ChessGameService.getPieceImages(chessGame));
			return render(model, "/chess.html");
		});

		get("/select", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("roomNumbers", ChessGameService.getRoomNumbers());
			return render(model, "/select_game.html");
		});

		post("/load", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			int roomNumber = Integer.parseInt(req.queryParams("room-number"));

			Player turn = ChessGameService.loadTurn(roomNumber);
			ChessBoard chessBoard = new ChessBoard();
			List<Piece> pieces = ChessGameService.loadChessPieces(roomNumber);
			for (Piece piece : pieces) {
				chessBoard.addPiece(piece);
			}
			ChessGame chessGame = new ChessGame(turn, chessBoard);

			model.put("turn", chessGame.getCurrentPlayer().name());
			model.put("roomNumber", roomNumber);
			model.put("board", ChessGameService.getPieceImages(chessGame));
			return render(model, "/chess.html");
		});

		post("/move", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			int roomNumber = Integer.parseInt(req.queryParams("room-number"));
			Player turn = ChessGameService.loadTurn(roomNumber);
			List<Piece> pieces = ChessGameService.loadChessPieces(roomNumber);
			ChessBoard chessBoard = new ChessBoard();
			for (Piece piece : pieces) {
				chessBoard.addPiece(piece);
			}
			ChessGame chessGame = new ChessGame(turn, chessBoard);
			int startX = Integer.parseInt(req.queryParams("start-x"));
			int startY = Integer.parseInt(req.queryParams("start-y"));
			int endX = Integer.parseInt(req.queryParams("end-x"));
			int endY = Integer.parseInt(req.queryParams("end-y"));
			try {
				chessGame.move(Position.getPosition(startX, startY), Position.getPosition(endX, endY));
			} catch (GameOverException e) {
				ChessGameService.gameOver(roomNumber);
				model.put("message", e.getMessage());
				return render(model, "/gameover.html");
			}

			ChessGameService.saveChessGame(roomNumber, chessGame);
			model.put("turn", chessGame.getCurrentPlayer().name());
			model.put("roomNumber", roomNumber);
			model.put("board", ChessGameService.getPieceImages(chessGame));
			return render(model, "/chess.html");
		});

		post("/status", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			int roomNumber = Integer.parseInt(req.queryParams("room-number"));
			Player turn = ChessGameService.loadTurn(roomNumber);
			List<Piece> pieces = ChessGameService.loadChessPieces(roomNumber);
			ChessBoard chessBoard = new ChessBoard();
			for (Piece piece : pieces) {
				chessBoard.addPiece(piece);
			}
			ChessGame chessGame = new ChessGame(turn, chessBoard);

			model.put("winner", "승자 : " + chessGame.findWinner().name());
			model.put("whiteScore", "백 점수 : " + chessGame.getPlayerScore(Player.WHITE).getScore());
			model.put("blackScore", "흑 점수 : " + chessGame.getPlayerScore(Player.BLACK).getScore());

			model.put("turn", chessGame.getCurrentPlayer().name());
			model.put("roomNumber", roomNumber);
			model.put("board", ChessGameService.getPieceImages(chessGame));
			return render(model, "/chess.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
