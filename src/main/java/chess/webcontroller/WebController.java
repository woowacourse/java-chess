package chess.webcontroller;

import static spark.Spark.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import chess.converter.web.RequestToCommandConverter;
import chess.domain.board.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.command.Start;
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
		startGame();
		move();

		exception(Exception.class, (exception, request, response) ->
			response.body(exception.getMessage()));
	}

	private static void showMain() {
		get("/", (req, res) -> render(new HashMap<>(), "index.html"));
	}

	private static void enterGame() {
		get("/new_game", (request, response) -> render(
			new HashMap<>(), "game.html"
		));
	}

	private static void startGame() {
		get("/start", (request, response) -> {
			GameState state = new Ready(BoardInitializer.generate())
				.proceed(new Start());
			states.add(state);
			return render(
				new BoardResponseDto(state.getBoard()).getValue(), "game.html"
			);
		});
	}

	private static void move() {
		post("/move", (request, response) -> {
			Command command = RequestToCommandConverter.from(request);

			if (states.isEmpty()) {
				throw new IllegalStateException("상태가 비었습니다.");
			}
			GameState state = states.peek().proceed(command);
			states.pop();
			states.add(state);
			return render(
				new BoardResponseDto(state.getBoard()).getValue(), "game.html"
			);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
