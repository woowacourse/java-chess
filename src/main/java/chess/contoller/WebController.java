package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.RoomsDto;
import chess.domain.dto.StatusDto;
import chess.domain.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
	private final ChessGameService chessGameService;

	public WebController(ChessGameService chessGameService) {
		this.chessGameService = chessGameService;
	}

	public void run() {
		staticFiles.location("/public");
		mainRendering();
		createRoom();
		joinRoom();
		movePiece();
		restartGame();
	}

	private void mainRendering() {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("room", new RoomsDto(chessGameService.findAllRooms()));
			return render(model, "index.html");
		});
	}

	private void createRoom() {
		post("/create", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				ChessGame chessGame = chessGameService.create(req.queryParams("room-name"));
				putGameInfoToModel(chessGame, model);
			} catch (RuntimeException e) {
				model.put("room", new RoomsDto(chessGameService.findAllRooms()));
				model.put("error", e.getMessage());
				return render(model, "index.html");
			}
			return render(model, "chess.html");
		});
	}

	private void joinRoom() {
		post("/join", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				ChessGame chessGame = chessGameService.load(req.queryParams("room-name"));
				putGameInfoToModel(chessGame, model);
				return render(model, "chess.html");
			} catch (RuntimeException e) {
				model.put("room", new RoomsDto(chessGameService.findAllRooms()));
				model.put("error", e.getMessage());
				return render(model, "index.html");
			}
		});
	}

	private void movePiece() {
		post("/chess", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				ChessGame chessGame = chessGameService.move(
						req.queryParams("room-name"), req.queryParams("source"), req.queryParams("target"));
				putGameInfoToModel(chessGame, model);
			} catch (RuntimeException e) {
				model.put("error", e.getMessage());
			}
			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void restartGame() {
		post("/restart", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			ChessGame chessGame = chessGameService.restart(req.queryParams("room-name"));
			return render(model, "index.html");
			putGameInfoToModel(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void putGameInfoToModel(ChessGame chessGame, Map<String, Object> model) {
		model.put("board", ChessBoardDto.of(chessGame));
		model.put("status", StatusDto.of(chessGame));
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}