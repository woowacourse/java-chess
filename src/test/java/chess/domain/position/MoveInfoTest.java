package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoveInfoTest {

	@Test
	void create_When_Success() {
		assertThat(MoveInfo.of("move a1 a2")).isInstanceOf(MoveInfo.class);
	}

	@Test
	void create_When_Fail_With_WrongPositionInput() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> MoveInfo.of("move a99 a10"))
			.withMessage("잘못된 위치값입니다.");
	}
}