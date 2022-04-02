package chess.webcontroller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.converter.web.RequestToCommandConverter;
import chess.converter.web.RequestToMapConverter;
import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.command.Start;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.service.GameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

	private static final String MAIN_PAGE = "index.html";
	private static final String GAME_PAGE = "game.html";

	private final GameService gameService = new GameService();

	public void run() {
		port(8081);
		staticFileLocation("/static");

		showMain(new HashMap<>());
		enterNewGame(new HashMap<>());
		startGame(new HashMap<>());
		continueGame(new HashMap<>());
		move(new HashMap<>());

		exception(Exception.class, (exception, request, response) ->
			response.body(exception.getMessage()));
	}

	private void showMain(Map<String, Object> model) {
		get("/", (req, res) -> render(model, MAIN_PAGE));
	}

	private void enterNewGame(Map<String, Object> model) {
		post("/new_game", (request, response) -> {
			Map<String, String> name = RequestToMapConverter.ofSingle(request);
			ChessGame game = new ChessGame(name.get("GAME_NAME"), new Ready(BoardInitializer.generate()));

			gameService.saveGame(game);

			model.putAll(BoardResponseDto.empty().getValue());
			model.put("GAME_NAME", game.getName());
			return render(model, GAME_PAGE);
		});
	}

	private void continueGame(Map<String, Object> model) {
		post("/continue_game", (request, response) -> {
			Map<String, String> name = RequestToMapConverter.ofSingle(request);

			ChessGame findGame = gameService.findGame(name.get("GAME_NAME"));
			fillModel(model, findGame);
			return render(model, GAME_PAGE);
		});
	}

	private void startGame(Map<String, Object> model) {
		get("/start/:GAME_NAME", (request, response) -> {
			ChessGame updatedGame = gameService.updateGame(new Start(), request.params(":GAME_NAME"));
			fillModel(model, updatedGame);
			return render(model, GAME_PAGE);
		});
	}

	private void move(Map<String, Object> model) {
		post("/move/:GAME_NAME", (request, response) -> {
			Command command = RequestToCommandConverter.from(request);

			ChessGame updatedGame = gameService.updateGame(command, request.params(":GAME_NAME"));

			if (updatedGame.isFinished()) {
				response.redirect("/");
				return null;
			}

			fillModel(model, updatedGame);
			return render(model, GAME_PAGE);
		});
	}

	private void fillModel(Map<String, Object> model, ChessGame game) {
		GameState state = game.getState();

		BoardResponseDto dto = BoardResponseDto.from(state.getBoard());
		model.putAll(dto.getValue());

		model.put("GAME_NAME", game.getName());
		model.put("TURN", state.getColor());
		model.put("ChessScore", state.generateScore());
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
