package web.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import domain.board.InvalidTurnException;
import domain.command.InvalidCommandException;
import domain.piece.position.InvalidPositionException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dto.ChessGameDto;
import web.dto.MoveCommandDto;
import web.service.BoardService;

public class WebController {
	private BoardService boardService;

	public WebController(BoardService boardService) {
		this.boardService = boardService;
	}

	public void run() {
		port(8080);
		staticFileLocation("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "game.html");
		});

		get("/start", (req, res) -> {
			String title = req.queryParams("title");
			ChessGameDto chessGameDto = boardService.createGame(title);
			Map<String, Object> model = new HashMap<>();
			model.put("data", chessGameDto);
			return render(model, "game.html");
		});

		post("/move", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			ChessGameDto chessGameDto;
			try {
				MoveCommandDto moveCommand = new MoveCommandDto(req.queryParams("moveCommand"));
				chessGameDto = boardService.move(moveCommand);
				model.put("data", chessGameDto);
			} catch (InvalidCommandException | InvalidTurnException | InvalidPositionException e) {
				model.put("error", e);
			}
			return render(model, "game.html");
		});

		get("/end", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "game.html");
		});

		get("/save", (req, res) -> {
			boardService.addBoard();
			Map<String, Object> model = new HashMap<>();
			model.put("message", "저장되었습니다.");
			return render(model, "game.html");
		});

		get("/loading", (req, res) -> {
			ChessGameDto chessGameDto = boardService.findByTitle(req.queryParams("title"));
			Map<String, Object> model = new HashMap<>();
			model.put("data", chessGameDto);
			return render(model, "game.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
