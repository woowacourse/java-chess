package chess;

import java.util.ArrayList;
import java.util.List;

import chess.exception.NotFoundPieceException;
import chess.exception.SamePositionException;
import chess.exception.UnmovableException;
import chess.piece.Piece;

public class ChessBoard {
	private final List<Piece> pieces;

	public ChessBoard() {
		pieces = new ArrayList<>();
	}

	public void addPiece(Piece newPiece) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(newPiece)) {
				throw new SamePositionException();
			}
		}
		pieces.add(newPiece);
	}

	public Score movePiece(Player player, Position start, Position end) {
		Piece startPiece = findPiece(start);
		if (!startPiece.isMine(player)) {
			throw new UnmovableException();
		}

		Path path = startPiece.move(end);
		checkValidPath(path);

		if (isExist(end)) {
			Piece endPiece = findPiece(end);
			if (endPiece.isMine(player)) {
				throw new UnmovableException();
			}
			pieces.remove(endPiece);
			return endPiece.getScore();
		}
		startPiece.changePosition(end);
		return new Score(0);
	}

	private void checkValidPath(Path path) {
		while (path.next()) {
			Position position = path.getCurrentPosition();
			if (isExist(position)) {
				throw new UnmovableException();
			}
		}
	}

	private Piece findPiece(Position position) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(position)) {
				return piece;
			}
		}
		throw new NotFoundPieceException();
	}

	private boolean isExist(Position position) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(position)) {
				return true;
			}
		}
		return false;
	}
}
