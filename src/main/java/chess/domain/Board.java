package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.direction.DirectionGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceInitializer;

public class Board {

	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = PieceInitializer.generate();
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = new HashMap<>(pieces);
	}

	public void movePiece(Position from, Position to) {
		Optional<Piece> piece = findPiece(from);
		validateEmpty(piece);
		Piece fromPiece = piece.get();

		validateMovable(fromPiece, from, to);

		Direction direction = DirectionGenerator.generateOfWhitePawn(from, to).get();

		piece = findPiece(to);
		if (piece.isEmpty()) {
			move(from, to, fromPiece);
			return;
		}

		Piece toPiece = piece.get();
		validateSameColor(fromPiece, toPiece);

		validateDiagonalEnemy(fromPiece, toPiece, direction);

		move(from, to, fromPiece);
	}

	private void validateEmpty(Optional<Piece> piece) {
		if (piece.isEmpty()) {
			throw new NoSuchElementException();
		}
	}

	private void validateMovable(Piece piece, Position from, Position to) {
		if (!piece.isMovable(from, to)) {
			throw new IllegalArgumentException();
		}
	}

	private void validateSameColor(Piece fromPiece, Piece toPiece) {
		if (fromPiece.isSameColor(toPiece)) {
			throw new IllegalArgumentException();
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

	public Optional<Piece> findPiece(Position position) {
		return pieces.entrySet().stream()
			.filter(entry -> entry.getKey().equals(position))
			.map(Map.Entry::getValue)
			.findFirst();
	}
}
