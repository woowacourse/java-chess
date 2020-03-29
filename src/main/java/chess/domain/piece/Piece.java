package chess.domain.piece;

import chess.domain.piece.movable.Movable;
import chess.domain.piece.pieces.PieceInitializer;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Piece {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "말을 생성할 수 없습니다.";

	private Position position;
	private final Movable movable;
	private final Color color;
	private final PieceType pieceType;


	public Piece(Position position, PieceType pieceType, Movable movable, Color color) {
		validate(position, movable);
		this.position = position;
		this.pieceType = pieceType;
		this.movable = movable;
		this.color = color;
	}

	public Piece(PieceInitializer pieceInitializer) {
		this(pieceInitializer.getPosition(), pieceInitializer.getPieceType(), pieceInitializer.getMovable(), pieceInitializer.getColor());
	}

	private void validate(Position position, Movable movable) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(movable, INVALID_INPUT_EXCEPTION_MESSAGE);
	}

	public Set<Position> createMovablePositions(List<Piece> pieces) {
		return movable.createMovablePositions(position, pieces, color);
	}

	public void move(Position position) {
		if (isSamePosition(position)) {
			throw new UnsupportedOperationException("같은 위치로 이동할 수 없습니다.");
		}
		this.position = position;
	}

	public boolean isSamePosition(Position position) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		return this.position.equals(position);
	}

	public boolean isSameColor(Color color) {
		Objects.requireNonNull(color, INVALID_INPUT_EXCEPTION_MESSAGE);
		return this.color.isSame(color);
	}

	public boolean isNotSameColor(Color color) {
		return !isSameColor(color);
	}

	public boolean isWhite() {
		return color.isWhite();
	}

	public boolean isKing() {
		return pieceType.isKing();
	}

	public boolean isPawn() {
		return pieceType.isPawn();
	}

	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public String getResource() {
		if(color.isWhite()) {
			return pieceType.getResource().toLowerCase();
		}
		return pieceType.getResource();
	}

	public double getScore() {
		return pieceType.getScore();
	}
}
