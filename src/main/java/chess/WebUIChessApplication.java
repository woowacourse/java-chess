package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.controller.ChessController;
import chess.domain.Result;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static final ChessController chessController = new ChessController();

	public static void main(String[] args) {
		staticFileLocation("/templates");
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> getBoardJson(chessController.find()));

		post("/move", (req, res) -> {
			try {
				return getBoardJson(chessController.move(req));
			} catch (IllegalArgumentException | UnsupportedOperationException e) {
				return e.getMessage();
			}
		});

		get("/isEnd", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			if (!chessController.isEnd()) {
				model.put("isEnd", false);
				return GSON.toJson(model);
			}
			model.put("isEnd", true);
			if (chessController.isWinWhiteTeam()) {
				model.put("message", "WHITE팀 승리!");
				return GSON.toJson(model);
			}
			model.put("message", "BLACK팀 승리!");
			return GSON.toJson(model);
		});

		get("/restart", (req, res) -> chessController.restart());

		get("/status", (req, res) -> {
			Result result = chessController.status();
			Map<String, Object> model = new HashMap<>();
			model.put("blackTeamScore", result.getBlackTeamScore());
			model.put("whiteTeamScore", result.getWhiteTeamScore());
			return GSON.toJson(model);
		});

	}

	private static String render(Map<String, Object> model, String templatePath) {
		return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
	}

	public static String getBoardJson(ChessBoard chessBoard) {
		Map<String, Object> model = new HashMap<>();
		for (ChessPiece chessPiece : chessBoard.findPieces()) {
			model.put(chessPiece.getPositionName(), chessPiece.getName());
		}
		return GSON.toJson(model);
	}

}
