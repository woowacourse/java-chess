package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Result;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {
	private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private final ChessService chessService;

	public ChessController() {
		chessService = new ChessService();
	}

	public String start() {
		Map<String, Object> model = new HashMap<>();
		return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, "index.html"));
	}

	public String init() {
		return getBoardJson(chessService.find());
	}

	public String move(Request req) {
		Position startPosition = Position.of(req.queryParams("startPosition"));
		Position targetPosition = Position.of(req.queryParams("targetPosition"));
		ChessBoard chessBoard = chessService.move(startPosition, targetPosition);
		return getBoardJson(chessBoard);
	}

	public String isEnd() {
		Map<String, Object> model = new HashMap<>();
		if (!chessService.isEnd()) {
			model.put("isEnd", false);
			return GSON.toJson(model);
		}
		model.put("isEnd", true);
		if (chessService.isWinWhiteTeam()) {
			model.put("message", "WHITE팀 승리!");
			return GSON.toJson(model);
		}
		model.put("message", "BLACK팀 승리!");
		return GSON.toJson(model);
	}

	public String restart() {
		chessService.restart();
		return init();
	}

	public String status() {
		Result result = chessService.status();
		Map<String, Object> model = new HashMap<>();
		model.put("blackTeamScore", result.getBlackTeamScore());
		model.put("whiteTeamScore", result.getWhiteTeamScore());
		return GSON.toJson(model);
	}

	private String getBoardJson(ChessBoard chessBoard) {
		Map<String, Object> model = new HashMap<>();
		for (ChessPiece chessPiece : chessBoard.findAll()) {
			model.put(chessPiece.getPositionName(), chessPiece.getName());
		}
		return GSON.toJson(model);
	}
}
