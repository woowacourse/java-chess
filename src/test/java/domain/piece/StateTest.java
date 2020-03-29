package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StateTest {
	@ParameterizedTest
	@CsvSource({"START, 1, true", "START, 2, true", "RUN, 1, true", "RUN, 2, false", "START, 3, false"})
	void movingTest(State state, int rowGap, boolean expected) {
		assertThat(state.getIsValidStepSize().apply(rowGap)).isEqualTo(expected);
	}
}
