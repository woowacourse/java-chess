package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.RoomName;
import chess.domain.Side;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.RoomsDto;
import chess.domain.dto.StatusDto;
import chess.domain.position.Position;
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
		ChessGame chessGame = ChessGame.start();

		mainRendering();
		createRoom(chessGame);
		joinRoom(chessGame);
		movePiece(chessGame);
		restartGame(chessGame);
	}

	private void createRoom(ChessGame chessGame) {
		post("/create", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			RoomName roomName = new RoomName(req.queryParams("room-name"));
			chessGameService.create(chessGame, roomName.getName());

			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void joinRoom(ChessGame chessGame) {
		post("/join", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			RoomName roomName = new RoomName(req.queryParams("room-name"));
			chessGameService.load(chessGame, roomName.getName());

			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void restartGame(ChessGame chessGame) {
		post("/restart", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			chessGame.restart();

			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void movePiece(ChessGame chessGame) {
		post("/chess", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				Position source = new Position(req.queryParams("source"));
				Position target = new Position(req.queryParams("target"));
				chessGame.move(source, target);
				chessGameService.save(chessGame, req.cookie("room-name"));
			} catch (RuntimeException e) {
				model.put("error", e.getMessage());
			}
			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void mainRendering() {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("room", new RoomsDto(chessGameService.findAllRooms()));
			return render(model, "index.html");
		});
	}

	private void transfer(ChessGame chessGame, Map<String, Object> model) {
		model.put("board", ChessBoardDto.of(chessGame.getBoard()));
		model.put("status", new StatusDto(chessGame.status(Side.WHITE), chessGame.status(Side.BLACK)));
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}