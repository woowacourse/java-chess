package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.StatusDto;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

	public void run() {
		staticFiles.location("/public");
		ChessGame chessGame = ChessGame.start();

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/chess", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			transfer(chessGame, model);
			return render(model, "chess.html");
		});

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

	private void transfer(ChessGame chessGame, Map<String, Object> model) {
		model.put("board", ChessBoardDto.of(chessGame.getBoard()));
		model.put("status", new StatusDto(chessGame.status(Side.WHITE), chessGame.status(Side.BLACK)));
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

