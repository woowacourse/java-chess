package chess.controller;

import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.GameDaoImpl;
import chess.domain.board.Position;
import chess.dto.ErrorResponseDto;
import chess.dto.GameDto;
import chess.dto.GamesDto;
import chess.dto.MoveDto;
import chess.dto.StatusDto;
import chess.dto.WinnerDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final ChessService chessService;

	public WebChessController() {
		this.chessService = new ChessService(new GameDaoImpl());
	}

	public void run() {
		Gson gson = new Gson();

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "start.html");
		});

		post("/start", (req, res) -> {
			GameDto gameDto = chessService.start(req.queryParams("name"));
			Map<String, Object> model = new HashMap<>();
			model.put("gameId", gameDto.getGameId());
			return render(model, "chess.html");
		});

		get("/game/:id", (req, res) -> {
			GameDto gameDto = chessService.load(Integer.parseInt(req.params(":id")));
			Map<String, Object> model = new HashMap<>();
			model.put("gameId", gameDto.getGameId());
			return render(model, "chess.html");
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

		get("/findGames", (req, res) -> {
			GamesDto gamesDto = chessService.findAll();
			Map<String, Object> model = new HashMap<>();
			model.put("games", gamesDto.getGames());
			System.out.println(model);
			return render(model, "load.html");
		});

		delete("/delete/:id", (req, res) -> {
			chessService.delete(Integer.parseInt(req.params(":id")));
			return req.params(":id");
		});

		handleException(gson, IllegalArgumentException.class);
		handleException(gson, IllegalStateException.class);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private void handleException(Gson gson, Class<? extends RuntimeException> exceptionClass) {
		exception(exceptionClass, (e, req, res) -> {
			System.out.println(e.getMessage());
			res.status(400);
			res.body(gson.toJson(new ErrorResponseDto(e.getMessage())));
		});
	}
}
