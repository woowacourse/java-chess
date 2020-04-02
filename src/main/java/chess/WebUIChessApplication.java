package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.factory.BoardFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final ChessBoard chessBoard = BoardFactory.createBoard();

	public static void main(String[] args) {
		staticFileLocation("/templates");
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			for (ChessPiece chessPiece : chessBoard.findAll()) {
				Position position = chessPiece.getPosition();
				model.put(position.toString(), chessPiece);
			}
			return gson.toJson(model);
		});

		get("/move/:position", (req, res) -> {
			String[] positions = req.params("position").split(" ");
			try {
				chessBoard.move(Position.of(positions[0]), Position.of(positions[1]));
				return true;
			} catch (RuntimeException e) {
				return false;
			}
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}


