package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public class FinishedTest {
	@Test
	@DisplayName("피니시드 생성")
	void constructor() {
		assertThat(new Finished()).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("게임 종료 후 시작시 예외 발생")
	void start() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> new Finished().start());
	}

	@Test
	@DisplayName("게임 종료 후 종료 시 예외 발생")
	void end() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> new Finished().end());
	}

	@Test
	@DisplayName("게임 종료 후 이동시 예외 발생")
	void move() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> new Finished().move(Position.from("a2"), Position
						.from("a3")));
	}

	@Test
	@DisplayName("게임 종료 후 체스판 확인")
	void board() {
		assertThat(new Finished().board()).isInstanceOf(Board.class);
	}

	@Test
	@DisplayName("게임 종료 상태는 참이다")
	void isFinished() {
		assertThat(new Finished().isFinished()).isTrue();
	}
}
