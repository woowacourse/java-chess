package chess.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommandTest {

	@Test
	void create_Success() {
		assertThat(Command.of("start")).isEqualTo(Command.START);
		assertThat(Command.of("end")).isEqualTo(Command.END);
		assertThat(Command.of("move a1 b2")).isEqualTo(Command.MOVE);
		assertThat(Command.of("status")).isEqualTo(Command.STATUS);
	}

	@Test
	void create_Fail() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Command.of("start  "))
			.withMessage("잘못된 명령어 입력입니다.");
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Command.of("move"))
			.withMessage("잘못된 명령어 입력입니다.");
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Command.of("start d "))
			.withMessage("잘못된 명령어 입력입니다.");
	}
}