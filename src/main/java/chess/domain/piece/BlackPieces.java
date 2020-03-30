package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.piece.state.Moved;

public class BlackPieces {
	private static final double INITIAL_PAWNS_STATUS = 0.0D;
	private static final int DEFAULT_PAWN_COUNT = 1;
	private static final double DEFAULT_PAWNS_STATUS = 1.0D;
	private static final double SAME_FILE_PAWNS_STATUS = 0.5D;

	private Map<Position, Piece> pieces;

	public BlackPieces(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public List<Position> findTrace(Position source, Position target) {
		if (!pieces.containsKey(source)) {
			throw new UnsupportedOperationException("움직일 수 있는 말이 아닙니다.");
		}

		Piece piece = pieces.get(source);
		return piece.movingTrace(source, target);
	}

	public boolean hasPiece(Position source) {
		return pieces.containsKey(source);
	}

	public boolean isKingDie() {
		return pieces.keySet().stream()
			.noneMatch(position -> pieces.get(position).isKing());
	}

	public void moveFromTo(Position source, Position target) {
		Piece piece = pieces.remove(source);
		piece.state = new Moved();
		pieces.put(target, piece);
	}

	public void kill(Position target) {
		pieces.remove(target);
	}

	public double calculateStatus() {
		return calculatePawnStatus() + pieces.keySet().stream()
			.filter(position -> !pieces.get(position).isPawn())
			.mapToDouble(position -> pieces.get(position).score())
			.sum();
	}

	private double calculatePawnStatus() {
		double pawnsStatus = INITIAL_PAWNS_STATUS;

		for (File file : File.values()) {
			int pawnCount = (int)pieces.keySet().stream()
				.filter(position -> (position.getColumn() == file.getColumn()) && (pieces.get(position).isPawn()))
				.count();
			if (pawnCount == DEFAULT_PAWN_COUNT) {
				pawnsStatus += DEFAULT_PAWNS_STATUS;
			} else {
				pawnsStatus += (SAME_FILE_PAWNS_STATUS * pawnCount);
			}
		}
		return pawnsStatus;
	}

	public Piece getPiece(Position position) {
		return pieces.get(position);
	}
}
