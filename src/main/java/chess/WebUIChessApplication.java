package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.PiecesManager;
import chess.domain.piece.WhitePiecesFactory;
import chess.view.OutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static Game game;
	private static Map<String, Object> model = new HashMap<>();

	public static void main(String[] args) {
		initialize();
		//start();
		//running();
		//end();
		//status();
	}

	private static void initialize() {
		init();
		get("/", (req, res) -> {
			return render(model, "index.html");
		});
	}

	private static void init() {
		game = new Game(new PiecesManager(WhitePiecesFactory.create(), BlackPiecesFactory.create()),
			new Board());
	}

	private static void movePiece(String source, String target) {
		try {
			game.movePieceFromTo(Position.of(source), Position.of(target));
		} catch (IllegalArgumentException | UnsupportedOperationException e) {
			model.put("error", e.getMessage());
		}
	}

	private static boolean isKingDie() {
		if (game.isKingDie()) {
			OutputView.printKingDie();
			return true;
		}
		return false;
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
