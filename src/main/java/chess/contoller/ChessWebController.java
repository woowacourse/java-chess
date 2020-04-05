package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.dto.ChessBoardDto;
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
			model.put("board", ChessBoardDto.of(chessGame.getBoard()));
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

			model.put("board", ChessBoardDto.of(chessGame.getBoard()));
			return render(model, "chess.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

