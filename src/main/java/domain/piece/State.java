package domain.piece;

import java.util.function.Function;

public enum State {
	START(State::isStartStepSize),
	RUN(State::isRunStepSize);

	private Function<Integer, Boolean> isValidStepSize;

	State(Function<Integer, Boolean> stepSize) {
		this.isValidStepSize = stepSize;
	}

	public Function<Integer, Boolean> getIsValidStepSize() {
		return isValidStepSize;
	}

	private static boolean isStartStepSize(int stepSize) {
		return stepSize == 1 || stepSize == 2;
	}

	private static boolean isRunStepSize(int stepSize) {
		return stepSize == 1;
	}
}
