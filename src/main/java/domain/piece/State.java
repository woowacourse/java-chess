package domain.piece;

import java.util.function.Function;

public enum State {
	START(State::isStartStepSize),
	RUN(State::isRunStepSize);

	private static final int START_OR_RUN_STEP_SIZE = 1;
	private static final int START_STEP_SIZE = 2;

	private Function<Integer, Boolean> isValidStepSize;

	State(Function<Integer, Boolean> stepSize) {
		this.isValidStepSize = stepSize;
	}

	public Function<Integer, Boolean> getIsValidStepSize() {
		return isValidStepSize;
	}

	private static boolean isStartStepSize(int stepSize) {
		return stepSize == START_OR_RUN_STEP_SIZE || stepSize == START_STEP_SIZE;
	}

	private static boolean isRunStepSize(int stepSize) {
		return stepSize == START_OR_RUN_STEP_SIZE;
	}
}
