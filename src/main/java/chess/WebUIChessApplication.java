package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static ChessService chessService;

	public static void main(String[] args) {
		staticFileLocation("/templates");
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			chessService = new ChessService();
			return chessService.getBoardJson();
		});

		post("/move", (req, res) -> {
			return chessService.move(req);
		});

		get("/isEnd", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return chessService.isEnd();
		});

		get("/restart", (req, res) -> {
			return chessService.restart();
		});

	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

}
