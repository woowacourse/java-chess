package chess.domain.game;

import java.util.Arrays;

public enum OperationType {
	START("start"),
	MOVE("move"),
	END("end"),
	STATUS("status");

	private static final String INVALID_OPERATION_EXCEPTION_MESSAGE = "잘못된 명령입니다.";

	private final String name;

	OperationType(String name) {
		this.name = name;
	}

	public static OperationType of(String name) {
		return Arrays.stream(OperationType.values())
				.filter(operationType -> operationType.name.equals(name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(INVALID_OPERATION_EXCEPTION_MESSAGE));
	}

	public void checkFirstOperations() {
		if (!isStart() && !isEnd()) {
			throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
		}
	}

	public boolean isStart() {
		return START.equals(this);
	}

	public boolean isMove() {
		return MOVE.equals(this);
	}

	public boolean isEnd() {
		return END.equals(this);
	}

	public boolean isStatus() {
		return STATUS.equals(this);
	}
}
