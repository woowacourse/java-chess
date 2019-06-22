package chess;

import java.util.ArrayList;
import java.util.List;

import chess.exception.SamePositionException;
import chess.piece.Piece;

public class ChessBoard {
	private final List<Piece> pieces;

	public ChessBoard() {
		pieces = new ArrayList<>();
	}

	public void addPiece(Piece newPiece) {
		if (hasSamePosition(newPiece)) {
			throw new SamePositionException();
		}
		pieces.add(newPiece);
	}

	private boolean hasSamePosition(Piece newPiece) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(newPiece));
	}

	public Piece findPiece(Position position) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(position)) {
				return piece;
			}
		}
		return null;
	}

	public boolean isMovable(Path path) {
		return path.getPath().stream()
				.noneMatch(this::isExist);
	}

	private boolean isExist(Position position) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}
}
