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
import chess.repository.ChessGameRepository;
import chess.service.ChessGameService;
import chess.converter.RequestToCommandConverter;
import chess.converter.RequestToMapConverter;
import chess.webcontroller.dto.BoardResponseDto;
import chess.webcontroller.dto.GameResponseDto;
import chess.webcontroller.dto.NameResponseDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

	private static final String MAIN_PAGE = "index.html";
	private static final String GAME_PAGE = "game.html";
	private static final String FILE_LOCATION = "/static";

	private static final int PORT_NUMBER = 8081;

	private static final String MAIN_URL = "/";
	private static final String NEW_GAME_URL = "/new_game";
	private static final String START_URL = "/start";
	private static final String MOVE_URL = "/move";
	private static final String DELETE_URL = "/delete";

	private static final String SLASH = "/";
	private static final String PATH_VARIABLE_NAME = ":name";
	private static final String NAMES = "names";
	private static final String NAME = "name";

	private final ChessGameService gameService;

	public WebController() {
		this.gameService = new ChessGameService(new ChessGameRepository());
	}

	public void run() {
		port(PORT_NUMBER);
		staticFileLocation(FILE_LOCATION);

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
		get(MAIN_URL, (req, res) -> {
			List<NameResponseDto> nameDtos = gameService.findAllGames().stream()
				.map(NameResponseDto::new)
				.collect(Collectors.toList());
			model.put(NAMES, nameDtos);
			return render(model, MAIN_PAGE);
		});
	}

	private void enterNewGame(Map<String, Object> model) {
		post(NEW_GAME_URL, (request, response) -> {
			Map<String, String> name = RequestToMapConverter.ofSingle(request);
			ChessGame game = new ChessGame(name.get(NAME), new Ready(BoardInitializer.generate()));

			gameService.saveGame(game);

			fillModelEmptyBoard(model, game);
			return render(model, GAME_PAGE);
		});
	}

	private void startGame(Map<String, Object> model) {
		get(START_URL + SLASH + PATH_VARIABLE_NAME, (request, response) -> {
			ChessGame updatedGame = gameService.updateGame(new Start(), request.params(PATH_VARIABLE_NAME));
			model.putAll(new GameResponseDto(updatedGame).getValue());
			return render(model, GAME_PAGE);
		});
	}

	private void continueGame(Map<String, Object> model) {
		get(SLASH + PATH_VARIABLE_NAME, (request, response) -> {
			ChessGame findGame = gameService.findGame(request.params(PATH_VARIABLE_NAME));
			if (findGame.isReady()) {
				fillModelEmptyBoard(model, findGame);
				return render(model, GAME_PAGE);
			}
			model.putAll(new GameResponseDto(findGame).getValue());
			return render(model, GAME_PAGE);
		});
	}

	private void fillModelEmptyBoard(Map<String, Object> model, ChessGame game) {
		model.putAll(BoardResponseDto.empty().getValue());
		model.put(NAME, game.getName());
	}

	private void move(Map<String, Object> model) {
		post(MOVE_URL + SLASH + PATH_VARIABLE_NAME, (request, response) -> {
			Command command = RequestToCommandConverter.from(request);
			ChessGame updatedGame = gameService.updateGame(command, request.params(PATH_VARIABLE_NAME));

			if (updatedGame.isFinished()) {
				response.redirect(MAIN_URL);
				return null;
			}

			model.putAll(new GameResponseDto(updatedGame).getValue());
			return render(model, GAME_PAGE);
		});
	}

	private void deleteGame() {
		post(DELETE_URL + SLASH + PATH_VARIABLE_NAME, (request, response) -> {
			gameService.deleteGame(request.params(PATH_VARIABLE_NAME));
			response.redirect(MAIN_URL);
			return null;
		});
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
