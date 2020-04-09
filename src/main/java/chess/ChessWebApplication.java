package chess;

import chess.domain.chessboard.ChessBoard;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebApplication {
	private static final String EMPTY_NAME = "";

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final ChessService chessService = new ChessService();
	private static ChessBoard chessBoard;

	public static void main(String[] args) {
		staticFileLocation("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			chessBoard = new ChessBoard();
			chessService.DBInit(chessBoard);
			Map<String, Object> model = chessService.getPiecesInfo();
			return gson.toJson(model);
		});

		get("/continue", (req, res) -> {
			Map<String, Object> model = chessService.getPiecesInfo();
			chessBoard = chessService.chessBoardContinue();
			return gson.toJson(model);
		});

		post("/movePiece", (req, res) -> {
			Map<String, Object> model = chessService.getMoveInfo(req, chessBoard);
			chessService.move(req, chessBoard);
			return gson.toJson(model);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
