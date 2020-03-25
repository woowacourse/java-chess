package chess.domain;

import java.util.Objects;

public class Position {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "옳지 않은 좌표 입력입니다.";
	private final Row row;
	private final Column column;

	public Position(String position) {
		validate(position);
		this.row = Row.of(position.substring(0, 1));
		this.column = Column.of(position.substring(1, 2));
	}

	private void validate(String position) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		if (position.isEmpty()) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}
}
