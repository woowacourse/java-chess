package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.Movable;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "말을 생성할 수 없습니다.";

	private Position position;
	private final String name;
	private final Movable movable;
	private final Color color;
	private final double score;


	public Piece(Position position, String name, Movable movable, Color color, double score) {
		validate(position, name, movable);
		this.position = position;
		this.name = name;
		this.movable = movable;
		this.color = color;
		this.score = score;
	}

	private void validate(Position position, String name, Movable movable) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(name, INVALID_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(movable, INVALID_INPUT_EXCEPTION_MESSAGE);

		if (name.isEmpty()) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public Set<Position> createMovablePositions(List<Piece> pieces) {
		return movable.createMovablePositions(position, pieces, color);
	}

	public void changePosition(Position position) {
		if(isSamePosition(position)) {
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

	public boolean isKing() {
		return false;
	}

	public boolean isPawn() {
		return false;
	}

	public boolean isWhite() {
		return color.isWhite();
	}

	public boolean isBlack() {
		return color.isBlack();
	}

	public Position getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public double getScore() {
		return score;
	}
}
