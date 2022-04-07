package chess.controller;

import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.GameDaoImpl;
import chess.domain.board.Position;
import chess.dto.request.MoveDto;
import chess.dto.response.ErrorDto;
import chess.dto.response.GameDto;
import chess.dto.response.GamesDto;
import chess.dto.response.StatusDto;
import chess.dto.response.WinnerDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final Gson gson;
	private final ChessService chessService;

	public WebChessController() {
		this.gson = new Gson();
		this.chessService = new ChessService(new GameDaoImpl());
	}

	public void run() {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "start.html");
		});

		get("/game/:id", (req, res) -> {
			GameDto gameDto = chessService.load(Integer.parseInt(req.params(":id")));
			Map<String, Object> model = new HashMap<>();
			model.put("gameId", gameDto.getGameId());
			return render(model, "chess.html");
		});

		get("/games", (req, res) -> {
			GamesDto gamesDto = chessService.findAll();
			Map<String, Object> model = new HashMap<>();
			model.put("games", gamesDto.getGames());
			return render(model, "load.html");
		});

		get("/start/:name", (req, res) -> {
			GameDto gameDto = chessService.start(req.params(":name"));
			return gson.toJson(gameDto);
		});

		get("/status", (req, res) -> {
			StatusDto statusDto = chessService.status();
			return gson.toJson(statusDto);
		});

		post("/move", (req, res) -> {
			MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
			Position source = PositionConvertor.to(moveDto.getSource());
			Position target = PositionConvertor.to(moveDto.getTarget());
			GameDto gameDto = chessService.move(moveDto.getGameId(), source, target);
			return gson.toJson(gameDto);
		});

		get("/end/:id", (req, res) -> {
			WinnerDto winnerDto = chessService.end(Integer.parseInt(req.params(":id")));
			return gson.toJson(winnerDto);
		});

		get("/load/:id", (req, res) -> {
			GameDto gameDto = chessService.load(Integer.parseInt(req.params(":id")));
			return gson.toJson(gameDto);
		});

		delete("/game/:id", (req, res) -> {
			chessService.delete(Integer.parseInt(req.params(":id")));
			return req.params(":id");
		});

		handleException(IllegalArgumentException.class);
		handleException(IllegalStateException.class);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private void handleException(Class<? extends RuntimeException> exceptionClass) {
		exception(exceptionClass, (e, req, res) -> {
			res.status(400);
			res.body(gson.toJson(new ErrorDto(e.getMessage())));
		});
	}
}
