package chess.webcontroller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.command.Start;
import chess.domain.state.Ready;
import chess.service.ChessGameService;
import chess.webcontroller.converter.RequestToCommandConverter;
import chess.webcontroller.converter.RequestToMapConverter;
import chess.webcontroller.dto.BoardResponseDto;
import chess.webcontroller.dto.GameResponseDto;
import chess.webcontroller.dto.NameResponseDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

	private static final String MAIN_PAGE = "index.html";
	private static final String GAME_PAGE = "game.html";

	private final ChessGameService gameService = new ChessGameService();

	public void run() {
		port(8081);
		staticFileLocation("/static");

		showMain(new HashMap<>());
		enterNewGame(new HashMap<>());
		startGame(new HashMap<>());
		continueGame(new HashMap<>());
		deleteGame();
		move(new HashMap<>());

		exception(Exception.class, (exception, request, response) ->
			response.body(exception.getMessage()));
	}

	private void showMain(Map<String, Object> model) {
		get("/", (req, res) -> {
			List<NameResponseDto> nameDtos = gameService.findAllGames().stream()
				.map(NameResponseDto::new)
				.collect(Collectors.toList());
			model.put("GAMES", nameDtos);
			return render(model, MAIN_PAGE);
		});
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
		get("/:name", (request, response) -> {
			ChessGame findGame = gameService.findGame(request.params(":name"));
			model.putAll(new GameResponseDto(findGame).getValue());
			return render(model, GAME_PAGE);
		});
	}

	private void deleteGame() {
		post("/delete/:name", (request, response) -> {
			gameService.deleteGame(request.params(":name"));
			response.redirect("/");
			return null;
		});
	}

	private void startGame(Map<String, Object> model) {
		get("/start/:GAME_NAME", (request, response) -> {
			ChessGame updatedGame = gameService.updateGame(new Start(), request.params(":GAME_NAME"));
			model.putAll(new GameResponseDto(updatedGame).getValue());
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

			model.putAll(new GameResponseDto(updatedGame).getValue());
			return render(model, GAME_PAGE);
		});
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
