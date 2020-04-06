package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.util.HashMap;
import java.util.Map;

import chess.domain.GameManager;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.coordinates.Coordinates;
import chess.dto.BoardResponseDto;
import chess.dto.MoveRequestDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/static");

		Board board = BoardFactory.createNewGame();
		GameManager gameManager = new GameManager(board);

		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.handlebars");
		});

		post("/move", (request, response) -> {
			MoveRequestDto moveRequestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
			try {
				gameManager.move(Coordinates.of(moveRequestDto.getFrom()), Coordinates.of(moveRequestDto.getTo()));
				return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		});

		get("/start", (request, response) -> {
			BoardResponseDto boardResponseDto = new BoardResponseDto(gameManager);
			return new Gson().toJson(boardResponseDto);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}