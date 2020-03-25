package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Position;

public class ReadyTest {
	@Test
	@DisplayName("레디 생성")
	void constructor() {
		assertThat(new Ready()).isInstanceOf(Ready.class);
	}

	@Test
	@DisplayName("게임 진행 상태 생성")
	void start() {
		assertThat(new Ready().start()).isInstanceOf(Playing.class);
	}

	@Test
	@DisplayName("게임 종료 상태 생성")
	void end() {
		assertThat(new Ready().end()).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("게임 시작전에 이동시 예외 발생")
	void move() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> new Ready().move(Position.from("a2"), Position
						.from("a3")));
	}

	@Test
	@DisplayName("게임 시작전에 체스판 확인시 예외 발생")
	void board() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> new Ready().board());
	}

	@Test
	@DisplayName("게임 준비 상태는 종료 상태가 아니다")
	void isFinished() {
		assertThat(new Ready().isFinished()).isFalse();
	}
}
