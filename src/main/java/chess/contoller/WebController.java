package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.StatusDto;
import chess.domain.position.Position;
import chess.domain.service.BoardService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
	private final BoardService boardService;

	public WebController(BoardService boardService) {
		this.boardService = boardService;
	}

	public void run() {
		staticFiles.location("/public");
		ChessGame chessGame = ChessGame.start();

		mainRendering();
		createRoom(chessGame);
		moveRendering(chessGame);
		restartRendering(chessGame);
	}

	private void createRoom(ChessGame chessGame) {
		post("/create", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			boardService.create(chessGame, req.queryParams("room-name"));
			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void restartRendering(ChessGame chessGame) {
		post("/restart", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			chessGame.restart();
			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void moveRendering(ChessGame chessGame) {
		post("/chess", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				Position source = new Position(req.queryParams("source"));
				Position target = new Position(req.queryParams("target"));
				chessGame.move(source, target);
			} catch (RuntimeException e) {
				model.put("error", e.getMessage());
			}

			transfer(chessGame, model);
			return render(model, "chess.html");
		});
	}

	private void mainRendering() {
		get("/", (req, res) -> {
			return render(new HashMap<>(), "index.html");
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