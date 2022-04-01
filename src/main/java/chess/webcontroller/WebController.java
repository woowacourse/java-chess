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

		showMain(new HashMap<>());
		enterGame(new HashMap<>());
		startGame(new HashMap<>());
		move(new HashMap<>());

		exception(Exception.class, (exception, request, response) ->
			response.body(exception.getMessage()));
	}

	private static void showMain(Map<String, Object> model) {
		get("/", (req, res) -> render(model, "index.html"));
	}

	private static void enterGame(Map<String, Object> model) {
		get("/new_game", (request, response) -> {
			model.putAll(BoardResponseDto.empty().getValue());
			return render(model, "game.html");
		});
	}

	private static void startGame(Map<String, Object> model) {
		get("/start", (request, response) -> {
			GameState state = new Ready(BoardInitializer.generate())
				.proceed(new Start());
			states.add(state);

			fillModel(model, state);
			return render(model, "game.html");
		});
	}

	private static void move(Map<String, Object> model) {
		post("/move", (request, response) -> {
			Command command = RequestToCommandConverter.from(request);

			if (states.isEmpty()) {
				throw new IllegalStateException("상태가 비었습니다.");
			}
			GameState state = states.peek().proceed(command);
			states.pop();

			if (state.isFinished()) {
				response.redirect("/");
				return null;
			}

			states.add(state);

			fillModel(model, state);
			return render(model, "game.html");
		});
	}

	private static void fillModel(Map<String, Object> model, GameState state) {
		BoardResponseDto dto = BoardResponseDto.from(state.getBoard());
		model.putAll(dto.getValue());
		model.put("TURN", state.getColor());
		model.put("ChessScore", state.generateScore());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
