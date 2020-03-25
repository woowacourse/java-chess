package chess.domain;

import java.util.Arrays;

public enum OperationType {
	START("start"),
	MOVE("move"),
	END("end"),
	STATUS("status");

	private final String name;

	OperationType(String name) {
		this.name = name;
	}

	public static OperationType of(String name) {
		// validate
		return Arrays.stream(OperationType.values())
				.filter(operationType -> operationType.name.equals(name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
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
