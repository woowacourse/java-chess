package domain.piece;

import java.util.function.Function;

public enum State {
	START(stepSize -> stepSize == 1 || stepSize == 2),
	RUN(stepSize -> stepSize == 1);

	private Function<Integer, Boolean> isValidStepSize;

	State(Function<Integer, Boolean> stepSize) {
		this.isValidStepSize = stepSize;
	}

	public Function<Integer, Boolean> getIsValidStepSize() {
		return isValidStepSize;
	}
}
