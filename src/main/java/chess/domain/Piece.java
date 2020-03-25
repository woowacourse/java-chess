package chess.domain;

import java.util.Objects;

public abstract class Piece {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "말을 생성할 수 없습니다.";

	private Position position;
	private final String name;

	public Piece(Position position, String name) {
		validate(position, name);
		this.position = position;
		this.name = name;
	}

	private void validate(Position position, String name) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		Objects.requireNonNull(name, INVALID_INPUT_EXCEPTION_MESSAGE);

		if (name.isEmpty()) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}
}
