package chess.service;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.PieceDao;
import chess.domain.chessPiece.piece.PieceMapper;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.linker.NameCodec.*;

public class ChessService {
	private static final PieceDao pieceDao = new PieceDao();

	public void initDatabase() throws Exception {
		ChessBoard chessBoard = new ChessBoard();
		List<Piece> pieces = chessBoard.getPieces();
		pieceDao.deleteAll();
		for (Piece piece : pieces) {
			pieceDao.addPiece(piece);
		}
	}

	public Map<String, Object> getPiecesInfo() throws Exception {
		HashMap<String, Object> model = new HashMap<>();
		List<Map<String, Object>> pieceInfos = pieceDao.readPieces();
		for (Map<String, Object> pieceInfo : pieceInfos) {
			String position = String.valueOf(pieceInfo.get("file")) + pieceInfo.get("rank");
			String pieceName = String.valueOf(pieceInfo.get("name"));
			model.put(position, pieceName);
		}
		return model;
	}

	public ChessBoard chessBoardFromDatabase() throws Exception {
		List<Piece> pieces = new ArrayList<>();
		List<Map<String, Object>> pieceInfos = pieceDao.readPieces();
		for (Map<String, Object> pieceInfo : pieceInfos) {
			String position = String.valueOf(pieceInfo.get("file")) + pieceInfo.get("rank");
			String pieceName = String.valueOf(pieceInfo.get("name"));
			pieces.add(PieceMapper.create(pieceName, position));
		}
		return new ChessBoard(pieces);
	}

	public Map<String, Object> getMoveInfo(final String source, final String target) throws Exception {
		Map<String, Object> model = new HashMap<>();
		ChessBoard chessBoard = chessBoardFromDatabase();
		Position sourcePosition = Position.of(source);
		Position targetPosition = Position.of(target);
		boolean isAttack = chessBoard.findPieceByPosition(targetPosition).isPresent();

		model.put("sourcePosition", source);
		model.put("targetPosition", target);
		model.put("sourcePieceType", findPieceType(sourcePosition, chessBoard));
		model.put("targetPieceType", findPieceType(targetPosition, chessBoard));
		model.put("isAttack", isAttack);

		chessBoard.movePiece(sourcePosition, targetPosition);
		updateDatabase(sourcePosition, targetPosition, isAttack);
		return model;
	}

	private String findPieceType(final Position position, final ChessBoard chessBoard) {
		Optional<Piece> piece = chessBoard.findPieceByPosition(position);
		return piece.map(Piece::getPieceName).orElse(EMPTY_NAME);
	}

	private void updateDatabase(final Position sourcePosition, final Position targetPosition, final boolean isAttack) throws SQLException {
		if (isAttack) {
			pieceDao.deletePiece(targetPosition);
		}
		pieceDao.updatePiece(sourcePosition, targetPosition);
	}
}
