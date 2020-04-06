package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Position;
import chess.dto.ResponseDto;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static final Gson GSON = new GsonBuilder().create();
	private static final ChessGameService CHESS_GAME_SERVICE = new ChessGameService();

	public static void main(String[] args) {
		Spark.staticFileLocation("assets");

		get("/", (req, res) -> render(new HashMap<>(), "index.html"));

		get("/games", (req, res) -> GSON.toJson(CHESS_GAME_SERVICE.games()));

		get("/game/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ResponseDto responseDto = CHESS_GAME_SERVICE.findById(id);
			if (responseDto.getStatusCode() == ResponseDto.SUCCESS) {
				Map<String, Object> model = new HashMap<>();
				model.put("id", id);
				return render(model, "game.html");
			}
			return "<script>location.replace('/')</script>";
		});

		notFound("<script>location.replace('/')</script>");

		get("/board/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return GSON.toJson(CHESS_GAME_SERVICE.findById(id));
		});

		post("/move", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			Position source = Position.from(req.queryParams("source"));
			Position target = Position.from(req.queryParams("target"));
			return GSON.toJson(CHESS_GAME_SERVICE.move(id, source, target));
		});

		post("/restart", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			return GSON.toJson(CHESS_GAME_SERVICE.restart(id));
		});

		post("/create", (req, res) -> GSON.toJson(CHESS_GAME_SERVICE.create()));
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
