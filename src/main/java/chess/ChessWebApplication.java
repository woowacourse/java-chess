package chess;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.PieceDao;
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
	private static final PieceDao pieceDao = new PieceDao();

	public static void main(String[] args) {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			pieceDao.deleteAll();
			List<Piece> pieces = chessBoard.getPieces();
			for (Piece piece : pieces) {
				pieceDao.addPiece(piece);
				model.put(piece.getPosition().toString(), piece.getPieceName());
			}
			return gson.toJson(model);
		});

		get("/continue", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			List<Map<String, Object>> pieces = pieceDao.readPieces();
			for (Map pieceMap : pieces) {
				String file = String.valueOf(pieceMap.get("file"));
				String rank = String.valueOf(pieceMap.get("rank"));
				model.put(file + rank, pieceMap.get("name"));
			}
			return gson.toJson(model);
		});


		post("/isMovable", (req, res) -> {
			String source = req.queryParams("sourcePosition");
			String target = req.queryParams("targetPosition");

			Position sourcePosition = Position.of(source);
			Position targetPosition = Position.of(target);

			String sourcePieceType = findPieceType(sourcePosition);
			String targetPieceType = findPieceType(targetPosition);
			boolean isAttack = chessBoard.findPieceByPosition(targetPosition).isPresent();

			Map<String, Object> model = new HashMap<>();
			model.put("source", source);
			model.put("target", target);
			model.put("sourcePieceType", sourcePieceType);
			model.put("isAttack", isAttack);
			model.put("targetPieceType", targetPieceType);

			chessBoard.movePiece(sourcePosition, targetPosition);

			//db처리
			if (isAttack) {
				pieceDao.deletePiece(targetPosition);
			}
			pieceDao.updatePiece(sourcePosition, targetPosition);

			return gson.toJson(model);
		});
	}

	private static String findPieceType(final Position position) {
		Optional<Piece> piece = chessBoard.findPieceByPosition(position);
		return piece.map(Piece::getPieceName).orElse("");
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
