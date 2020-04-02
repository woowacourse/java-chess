import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.GameManager;
import chess.piece.PieceFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private GameManager gameManager = new GameManager(new PieceFactory().createPieces());

	public void run() {
		port(8080);
		staticFileLocation("/public");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("board", gameManager.getBoard());
			model.put("state", gameManager.getGameState());
			return render(model, "index.html");
		});
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
