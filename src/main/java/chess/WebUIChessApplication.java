package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

	public static void main(String[] args) {
		port(8080);

		staticFileLocation("/public");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/chess", (req, res) -> {
			ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

			Map<String, Object> model = new HashMap<>(chessGame.getChessBoardDto().getChessBoard());
			return render(model, "chess.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
