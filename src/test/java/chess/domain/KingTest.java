package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KingTest {

	@Test
	void moveTo_When_Success() {
		King king = new King(Position.of("c3"));
		king.moveTo(Position.of("c4"));

		assertThat(king.getPosition()).isEqualTo(Position.of("c4"));
	}

	@Test
	void moveTo_When_Fail() {
		King king = new King(Position.of("c3"));
		assertThatIllegalArgumentException()
			.isThrownBy(() -> king.moveTo(Position.of("c5")))
			.withMessage("킹의 이동 범위가 아닙니다.");
	}
}