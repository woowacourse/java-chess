package chess;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final ChessBoard chessBoard = new ChessBoard();

	public static void main(String[] args) {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		post("/isMovable", (req, res) -> {
			String source = req.queryParams("sourcePosition");
			String target = req.queryParams("targetPosition");

			Position sourcePosition = Position.of(source);
			Position targetPosition = Position.of(target);

			Map<String, Object> model = new HashMap<>();
			chessBoard.movePiece(sourcePosition, targetPosition);
			model.put("source", source);
			model.put("target", target);

			String data = gson.toJson(model);
			return data;
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
