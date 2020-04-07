package chess;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.PieceCreator;
import chess.domain.chessPiece.piece.PieceDao;
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
	private static final String EMPTY_NAME = "";

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

			databaseInit(model, pieces);
			return gson.toJson(model);
		});

		get("/continue", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			updateChessBoardFromDatabase(model);
			return gson.toJson(model);
		});

		post("/movePiece", (req, res) -> {
			Position sourcePosition = Position.of(req.queryParams("sourcePosition"));
			Position targetPosition = Position.of(req.queryParams("targetPosition"));

			boolean isAttack = chessBoard.findPieceByPosition(targetPosition).isPresent();

			Map<String, Object> model = new HashMap<>();
			model.put("sourcePosition", req.queryParams("sourcePosition"));
			model.put("targetPosition", req.queryParams("targetPosition"));
			model.put("sourcePieceType", findPieceType(sourcePosition));
			model.put("targetPieceType", findPieceType(targetPosition));
			model.put("isAttack", isAttack);

			chessBoard.movePiece(sourcePosition, targetPosition);
			updateDatabase(sourcePosition, targetPosition, isAttack);

			return gson.toJson(model);
		});
	}

	private static void databaseInit(final Map<String, Object> model, final List<Piece> pieces) throws SQLException {
		pieceDao.deleteAll();
		for (Piece piece : pieces) {
			pieceDao.addPiece(piece);
			model.put(piece.getPosition().toString(), piece.getPieceName());
		}
	}

	private static void updateChessBoardFromDatabase(final Map<String, Object> model) throws SQLException {
		List<Map<String, Object>> pieceInfos = pieceDao.readPieces();
		List<Piece> pieces = new ArrayList<>();
		for (Map pieceInfo : pieceInfos) {
			String position = String.valueOf(pieceInfo.get("file")) + pieceInfo.get("rank");
			String pieceName = String.valueOf(pieceInfo.get("name"));
			Piece piece = PieceCreator.create(pieceName, position);
			pieces.add(piece);
			model.put(position, pieceName);
		}
		chessBoard = new ChessBoard(pieces);
	}

	private static void updateDatabase(final Position sourcePosition, final Position targetPosition, final boolean isAttack) throws SQLException {
		if (isAttack) {
			pieceDao.deletePiece(targetPosition);
		}
		pieceDao.updatePiece(sourcePosition, targetPosition);
	}

	private static String findPieceType(final Position position) {
		Optional<Piece> piece = chessBoard.findPieceByPosition(position);
		return piece.map(Piece::getPieceName).orElse(EMPTY_NAME);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
