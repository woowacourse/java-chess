package chess.domain.game.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Position;

public class ReadyTest {
	private State state = new Ready();

	@Test
	@DisplayName("레디 생성")
	void constructor() {
		assertThat(state).isInstanceOf(Ready.class);
	}

	@Test
	@DisplayName("게임 진행 상태 생성")
	void start() {
		assertThat(state.start()).isInstanceOf(Playing.class);
	}

	@Test
	@DisplayName("게임 종료 상태 생성")
	void end() {
		assertThat(state.end()).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("게임 시작전에 이동시 예외 발생")
	void move() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> state.move(Position.from("a2"), Position.from("a3")));
	}

	@Test
	@DisplayName("게임 시작전에 체스판 확인시 예외 발생")
	void board() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> state.board());
	}

	@Test
	@DisplayName("게임 준비 상태는 종료 상태가 아니다")
	void isFinished() {
		assertThat(state.isFinished()).isFalse();
	}
}
