package chess.domain.game;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public enum OperationType {
	START("start", () -> {
		throw new UnsupportedOperationException(OperationType.INVALID_OPERATION_EXCEPTION_MESSAGE);
	}),
	MOVE("move", () -> true),
	END("end", () -> false),
	STATUS("status", () -> true);

	private static final String INVALID_OPERATION_EXCEPTION_MESSAGE = "잘못된 명령입니다.";

	private final String name;
	private final BooleanSupplier operationHandler;

	OperationType(String name, BooleanSupplier operationHandler) {
		this.name = name;
		this.operationHandler = operationHandler;
	}

	public static OperationType of(String name) {
		validate(name);
		return Arrays.stream(OperationType.values())
				.filter(operationType -> operationType.name.equals(name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(INVALID_OPERATION_EXCEPTION_MESSAGE));
	}

	private static void validate(String name) {
		Objects.requireNonNull(name, INVALID_OPERATION_EXCEPTION_MESSAGE);
		if (name.isEmpty()) {
			throw new IllegalArgumentException(INVALID_OPERATION_EXCEPTION_MESSAGE);
		}
	}

	public void checkFirstOperations() {
		if (!isStart() && !isEnd()) {
			throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
		}
	}

	public boolean canExecuteMore() {
		return operationHandler.getAsBoolean();
	}

	public boolean isStart() {
		return START.equals(this);
	}

	public boolean isEnd() {
		return END.equals(this);
	}

	public boolean isMove() {
		return MOVE.equals(this);
	}
}
