package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class Board {

	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = PieceInitializer.generate();
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = new HashMap<>(pieces);
	}

	public Optional<Piece> findPiece(Position position) {
		return pieces.entrySet().stream()
			.filter(entry -> entry.getKey().equals(position))
			.map(Map.Entry::getValue)
			.findFirst();
	}

	public void movePiece(Position from, Position to) {
		Piece fromPiece = checkFromPieceEmpty(from);
		Direction direction = fromPiece.matchDirection(from, to);
		searchPiece(from, to, direction);
		checkTargetPosition(to, fromPiece, direction);

		move(from, to, fromPiece);
	}

	public boolean isWhite(Position position) {
		return checkFromPieceEmpty(position).isWhite();
	}

	private Piece checkFromPieceEmpty(Position from) {
		Optional<Piece> piece = findPiece(from);
		if (piece.isEmpty()) {
			throw new NoSuchElementException();
		}
		return piece.get();
	}

	private void searchPiece(Position from, Position to, Direction direction) {
		Position step = from.convert(new UnitPosition(0, 0));
		while (!step.equals(to)) {
			step = step.convert(direction.getUnitPosition());
			validateExistPiece(to, step);
		}
	}

	private void validateExistPiece(Position to, Position step) {
		if (findPiece(step).isPresent() && !step.equals(to)) {
			throw new IllegalArgumentException();
		}
	}

	private void checkTargetPosition(Position to, Piece fromPiece, Direction direction) {
		Optional<Piece> piece;
		piece = findPiece(to);
		if (piece.isPresent()) {
			Piece toPiece = piece.get();
			validateSameColor(fromPiece, toPiece);
			checkPieceIsPawn(fromPiece, direction, toPiece);
		}
	}

	private void validateSameColor(Piece fromPiece, Piece toPiece) {
		if (fromPiece.isSameColor(toPiece)) {
			throw new IllegalArgumentException();
		}
	}

	private void checkPieceIsPawn(Piece fromPiece, Direction direction, Piece toPiece) {
		if (fromPiece.isPawn()) {
			validateDiagonalEnemy(fromPiece, toPiece, direction);
		}
	}

	private void validateDiagonalEnemy(Piece fromPiece, Piece toPiece, Direction direction) {
		if (!direction.isDiagonal() || fromPiece.isSameColor(toPiece)) {
			throw new IllegalArgumentException();
		}
	}

	private void move(Position from, Position to, Piece piece) {
		this.pieces.remove(from);
		this.pieces.put(to, piece);
	}

	public Map<Position, Piece> getPieces() {
		return new HashMap<>(pieces);
	}
}
