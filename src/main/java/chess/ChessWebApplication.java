package chess;

import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final ChessService chessService = new ChessService();

	public static void main(String[] args) {
		staticFileLocation("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			chessService.initDatabase();
			Map<String, Object> model = chessService.getPiecesInfo();
			return gson.toJson(model);
		});

		get("/continue", (req, res) -> {
			Map<String, Object> model = chessService.getPiecesInfo();
			return gson.toJson(model);
		});

		post("/movePiece", (req, res) -> {
			String source = req.queryParams("source");
			String target = req.queryParams("target");

			Map<String, Object> model = chessService.move(source, target);
			return gson.toJson(model);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
