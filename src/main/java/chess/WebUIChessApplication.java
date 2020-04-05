package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.Ready;
import chess.service.GameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		Game game = new Game(new Ready(new Board()));
		port(8080);
		staticFiles.location("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		//새 게임 시작한다. 콘솔 관점에서 new Game(new Ready(new Board()))
		get("/chess/start", (req, res) ->
			"[{\"position\":{\"row\": 4, \"col\": 3}, \"piece\": {\"symbol\": \"p\", \"team\": \"black\"}},{ \"position\": {\"row\": 3, \"col\": 3},\"piece\":{\"symbol\":\"\",\"team\":\"empty\"}}]"
		);

		get("/chess/record", (req, res) ->
			new Gson().toJson(new GameService(game).calculateScore())
		);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
