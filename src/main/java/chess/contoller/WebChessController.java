package chess.contoller;

import chess.dao.ChessBoardDao;
import chess.dao.ChessGameDao;
import chess.domain.*;
import chess.domain.dto.ChessBoardAssembler;
import chess.domain.dto.PieceAssembler;
import chess.domain.dto.PieceNameConverter;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebChessController {
	private ChessGame chessGame;
	private ChessBoardDao chessBoardDao;
	private ChessGameDao chessGameDao;
	private int gameId;

	public WebChessController() {
		chessGame = new ChessGame(ChessBoardFactory.create());
		chessBoardDao = new ChessBoardDao();
		chessGameDao = new ChessGameDao();
	}

	public String startGame(Request request, Response response) {
		chessGame = new ChessGame(ChessBoardFactory.create());
		try {
			gameId = chessGameDao.add(chessGame.getTurn());
			chessBoardDao.addPieces(PieceAssembler.createDtos(chessGame.getChessBoard()), gameId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			renderStartError(e);
		}
		return render();
	}

	public String continueGame(Request request, Response response) {
		try {
			gameId = Integer.parseInt(request.queryParams("gameId"));
			List<Piece> pieces = chessBoardDao.findByGameId(gameId)
					.stream()
					.map(PieceNameConverter::toPiece)
					.collect(Collectors.toList());
			chessGame = new ChessGame(new ChessBoard(pieces), chessGameDao.findTrunByGameId(gameId));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return renderStartError(e);
		}
		return render();
	}

	public String runGame(Request request, Response response) {
		try {
			chessGame.move(new Position(request.queryParams("source")), new Position(request.queryParams("target")));
			chessBoardDao.deleteByGameId(gameId);
			chessBoardDao.addPieces(PieceAssembler.createDtos(chessGame.getChessBoard()), gameId);
			chessGameDao.updateTurn(gameId, chessGame.getTurn());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return renderError(e);
		}
		if (chessGame.isEnd()) {
			try {
				chessGameDao.deleteByGameId(gameId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				renderError(e);
			}
			return renderEnd();
		}
		return render();
	}

	public String showGame(Request request, Response response) {
		return render();
	}

	public String endGame(Request request, Response response) {
		chessGame.end();
		try {
			chessBoardDao.deleteByGameId(gameId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			renderError(e);
		}
		return renderEnd();
	}

	private String render() {
		ChessStatus chessStatus = chessGame.createStatus();
		Map<String, Object> model = new HashMap<>();
		model.put("piece", ChessBoardAssembler.create(chessGame.getChessBoard()));
		model.put("blackscore", chessStatus.calculateScore(Side.BLACK));
		model.put("whitescore", chessStatus.calculateScore(Side.WHITE));
		model.put("turn", chessGame.getTurn());
		model.put("id", gameId);
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "game.html"));
	}

	private String renderEnd() {
		Map<String, Object> model = new HashMap<>();
		model.put("turn", chessGame.getTurn());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "end.html"));
	}

	private String renderStartError(Exception e) {
		Map<String, Object> model = new HashMap<>();
		model.put("error", e.getMessage());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "starterror.html"));
	}

	private String renderError(Exception e) {
		Map<String, Object> model = new HashMap<>();
		model.put("error", e.getMessage());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "error.html"));
	}
}
