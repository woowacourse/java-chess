package chess.domain.game;

import chess.domain.position.PositionFactory;

import java.util.Arrays;
import java.util.Objects;

public enum OperationType {
	START("start", (chessGame, operations) -> {
		throw new UnsupportedOperationException(OperationType.INVALID_OPERATION_EXCEPTION_MESSAGE);
	}),
	MOVE("move", (chessGame, operations) -> {
		chessGame.move(PositionFactory.of(operations.getFirstArgument()), PositionFactory.of(operations.getSecondArgument()));
		return true;
	}),
	END("end", (chessGame, operations) -> false),
	STATUS("status", (chessGame, operations) -> true);

	private static final String INVALID_OPERATION_EXCEPTION_MESSAGE = "잘못된 명령입니다.";

	private final String name;
	private final Operate operate;

	OperationType(String name, Operate operate) {
		this.name = name;
		this.operate = operate;
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

	public boolean runOperate(ChessGame chessGame, Operations operations) {
		return operate.apply(chessGame, operations);
	}

	public boolean isStart() {
		return START.equals(this);
	}

	public boolean isEnd() {
		return END.equals(this);
	}
}
