package chess.contoller;

import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.domain.ChessStatus;
import chess.domain.Side;
import chess.domain.dto.ChessBoardAssembler;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class WebChessController {
	private ChessGame chessGame;

	public WebChessController() {
		chessGame = new ChessGame(ChessBoardFactory.create());
	}

	public String startGame(Request request, Response response) {
		chessGame = new ChessGame(ChessBoardFactory.create());
		return render();
	}

	public String runGame(Request request, Response response) {
		try {
			chessGame.move(new Position(request.queryParams("source")), new Position(request.queryParams("target")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return render(e);
		}
		if (chessGame.isEnd()) {
			return renderEnd();
		}
		return render();
	}

	public String endGame(Request request, Response response) {
		chessGame.end();
		return renderEnd();
	}

	private String renderEnd() {
		Map<String, Object> model = new HashMap<>();
		model.put("turn", chessGame.getTurn());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "end.html"));
	}

	private String render() {
		ChessStatus chessStatus = chessGame.createStatus();
		Map<String, Object> model = new HashMap<>();
		model.put("piece", ChessBoardAssembler.create(chessGame.getChessBoard()));
		model.put("blackscore", chessStatus.calculateScore(Side.BLACK));
		model.put("whitescore", chessStatus.calculateScore(Side.WHITE));
		model.put("turn", chessGame.getTurn());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "game.html"));
	}

	private String render(Exception e) {
		ChessStatus chessStatus = chessGame.createStatus();
		Map<String, Object> model = new HashMap<>();
		model.put("piece", ChessBoardAssembler.create(chessGame.getChessBoard()));
		model.put("blackscore", chessStatus.calculateScore(Side.BLACK));
		model.put("whitescore", chessStatus.calculateScore(Side.WHITE));
		model.put("turn", chessGame.getTurn());
		model.put("error", e.getMessage());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "game.html"));
	}
}
