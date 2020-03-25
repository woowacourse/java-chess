package chess.domain.piece;

import chess.domain.piece.movable.Movable;
import chess.domain.board.position.Position;

import java.util.Objects;
import java.util.Set;

public abstract class Piece {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "말을 생성할 수 없습니다.";

	private Position position;
	private final String name;
	private final Movable movable;

	public Piece(Position position, String name, Movable movable) {
		validate(position, name, movable);
		this.position = position;
		this.name = name;
		this.movable = movable;
	}

	private void validate(Position position, String name, Movable movable) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(name, INVALID_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(movable, INVALID_INPUT_EXCEPTION_MESSAGE);

		if (name.isEmpty()) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public Set<Position> getAvailablePositions() {
		return movable.move(position);
	}

	public Position getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}
}
