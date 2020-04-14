package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.dto.ChessBoardDto;
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
			model.put("room", chessGameService.findAllRooms());
			return render(model, "index.html");
		});
	}

	private void createRoom() {
		post("/create", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				String roomName = req.queryParams("room-name");
				ChessGame chessGame = chessGameService.create(roomName);
				putGameInfoToModel(roomName, chessGame, model);
			} catch (RuntimeException e) {
				model.put("room", chessGameService.findAllRooms());
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
				String roomName = req.queryParams("room-name");
				ChessGame chessGame = chessGameService.load(roomName);
				putGameInfoToModel(roomName, chessGame, model);
				return render(model, "chess.html");
			} catch (RuntimeException e) {
				model.put("room", chessGameService.findAllRooms());
				model.put("error", e.getMessage());
				return render(model, "index.html");
			}
		});
	}

	private void movePiece() {
		post("/chess", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				String roomName = req.queryParams("room-name");
				ChessGame chessGame = chessGameService.move(roomName,
						req.queryParams("source"), req.queryParams("target"));
				putGameInfoToModel(roomName, chessGame, model);
			} catch (RuntimeException e) {
				model.put("error", e.getMessage());
			}
			return render(model, "chess.html");
		});
	}

	private void restartGame() {
		post("/restart", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			String roomName = req.queryParams("room-name");
			ChessGame chessGame = chessGameService.restart(roomName);
			putGameInfoToModel(roomName, chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void putGameInfoToModel(String roomName, ChessGame chessGame, Map<String, Object> model) {
		model.put("room", roomName);
		model.put("board", ChessBoardDto.of(chessGame));
		model.put("status", StatusDto.of(chessGame));
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}