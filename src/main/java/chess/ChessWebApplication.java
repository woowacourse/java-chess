package chess;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.PieceDao;
import chess.domain.chessPiece.piece.PieceNameMatcher;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.*;

public class ChessWebApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final PieceDao pieceDao = new PieceDao();
	private static ChessBoard chessBoard;

	public static void main(String[] args) {
		staticFileLocation("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			Map<String, Object> model = new HashMap<>();

			chessBoard = new ChessBoard();
			List<Piece> pieces = chessBoard.getPieces();

			pieceDao.deleteAll();
			for (Piece piece : pieces) {
				pieceDao.addPiece(piece);
				model.put(piece.getPosition().toString(), piece.getPieceName());
			}
			return gson.toJson(model);
		});

		get("/continue", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			List<Map<String, Object>> pieces = pieceDao.readPieces();

			List<Piece> piecesOfChessBoard = new ArrayList<>();
			for (Map pieceMap : pieces) {
				String file = String.valueOf(pieceMap.get("file"));
				String rank = String.valueOf(pieceMap.get("rank"));
				String position = file + rank;
				String pieceType = String.valueOf(pieceMap.get("name"));
				Piece piece = PieceNameMatcher.create(pieceType, position);
				piecesOfChessBoard.add(piece);
				model.put(position, pieceType);
			}
			chessBoard = new ChessBoard(piecesOfChessBoard);
			return gson.toJson(model);
		});

		post("/isMovable", (req, res) -> {
			Position sourcePosition = Position.of(req.queryParams("sourcePosition"));
			Position targetPosition = Position.of(req.queryParams("targetPosition"));

			String sourcePieceType = findPieceType(sourcePosition);
			String targetPieceType = findPieceType(targetPosition);
			boolean isAttack = chessBoard.findPieceByPosition(targetPosition).isPresent();

			Map<String, Object> model = new HashMap<>();
			model.put("sourcePosition", req.queryParams("sourcePosition"));
			model.put("targetPosition", req.queryParams("targetPosition"));
			model.put("sourcePieceType", sourcePieceType);
			model.put("targetPieceType", targetPieceType);
			model.put("isAttack", isAttack);

			chessBoard.movePiece(sourcePosition, targetPosition);
			updateDataBase(sourcePosition, targetPosition, isAttack);

			return gson.toJson(model);
		});
	}

	private static void updateDataBase(final Position sourcePosition, final Position targetPosition, final boolean isAttack) throws SQLException {
		if (isAttack) {
			pieceDao.deletePiece(targetPosition);
		}
		pieceDao.updatePiece(sourcePosition, targetPosition);
	}

	private static String findPieceType(final Position position) {
		Optional<Piece> piece = chessBoard.findPieceByPosition(position);
		return piece.map(Piece::getPieceName).orElse("");
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
