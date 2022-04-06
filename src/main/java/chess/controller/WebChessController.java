package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.GameDao;
import chess.domain.board.Position;
import chess.domain.state.Ready;
import chess.dto.ErrorResponseDto;
import chess.dto.GameDto;
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
		this.chessService = new ChessService(new GameDao(), new Ready());
	}

	public void run() {
		Gson gson = new Gson();
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "chess.html");
		});

		get("/start", (req, res) -> {
			GameDto gameDto = chessService.start();
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

		get("/end", (req, res) -> {
			WinnerDto winnerDto = chessService.end();
			return gson.toJson(winnerDto);
		});

		handleException(gson, IllegalArgumentException.class);
		handleException(gson, IllegalStateException.class);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private void handleException(Gson gson, Class<? extends RuntimeException> exceptionClass) {
		exception(exceptionClass, (e, req, res) -> {
			res.status(400);
			res.body(gson.toJson(new ErrorResponseDto(e.getMessage())));
		});
	}
}
