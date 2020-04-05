package chess.contoller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.dto.ChessBoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

	public void run() {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/chess", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			ChessGame chessGame = ChessGame.start();
			ChessBoardDto chessBoardDto = ChessBoardDto.of(chessGame.getBoard());
			model.put("board", chessBoardDto);
			return render(model, "chess.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
