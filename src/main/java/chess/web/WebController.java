package chess.web;

import static spark.Spark.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.BoardInitializer;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

	private final static Deque<GameState> states = new ArrayDeque<>();

	public static void main(String[] args) {
		port(8081);
		staticFileLocation("/static");

		showMain();
		enterGame();

	}

	private static void showMain() {
		get("/", (req, res) -> render(new HashMap<>(), "index.html"));
	}

	private static void enterGame() {
		get("/new_game", (req, res) -> {
			GameState state = new Ready(BoardInitializer.generate());
			states.add(state);
			return render(
				new WebBoardDto(state.getBoard()).getValue(), "game.html"
			);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
