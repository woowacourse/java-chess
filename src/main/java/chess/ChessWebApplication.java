package chess;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.*;

public class ChessWebApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final ChessBoard chessBoard = new ChessBoard();

	public static void main(String[] args) {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			List<Piece> pieces = chessBoard.getPieces();
			for (Piece piece : pieces) {
				model.put(piece.getPosition().toString(), piece);
			}
			return gson.toJson(model);
		});

		post("/isMovable", (req, res) -> {
			String source = req.queryParams("sourcePosition");
			String target = req.queryParams("targetPosition");

			Position sourcePosition = Position.of(source);
			Position targetPosition = Position.of(target);
			chessBoard.movePiece(sourcePosition, targetPosition);

			String pieceType = findPieceType(sourcePosition);

			Map<String, Object> model = new HashMap<>();
			model.put("source", source);
			model.put("target", target);
			model.put("pieceType", pieceType);

			return gson.toJson(model);
		});
	}

	private static String findPieceType(final Position sourcePosition) {
		Optional<Piece> piece = chessBoard.findPieceByPosition(sourcePosition);
		return piece.map(Piece::getName).get();
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
