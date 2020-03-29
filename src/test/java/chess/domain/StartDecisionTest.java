package chess.domain;

import chess.controller.StartDecision;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StartDecisionTest {
	@ParameterizedTest
	@DisplayName("선택에 따라 옳은 결과가 나오는지 테스트")
	@CsvSource(value = {"start, START", "START, START", "end, END", "END, END"})
	void checkDecision(String decision, StartDecision expected) {
		assertThat(StartDecision.of(decision)).isEqualTo(expected);
	}
}
