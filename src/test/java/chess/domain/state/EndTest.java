package chess.domain.state;

import chess.domain.ChessBoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndTest {
	@Test
	@DisplayName("지원되지 않는 기능 예외처리 - Status")
	void calculateStatus() {
		State end = new End(ChessBoardFactory.create());
		assertThatThrownBy(end::createStatus)
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	@DisplayName("지원되지 않는 기능 예외처리 - Status")
	void move() {
		State end = new End(ChessBoardFactory.create());
		assertThatThrownBy(() -> end.move(new Position("a2"), new Position("a4")))
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	@DisplayName("게임이 끝났는 지 확인")
	void isEnd() {
		State end = new End(ChessBoardFactory.create());
		assertThat(end.isEnd()).isTrue();
	}

	@Test
	@DisplayName("지원되지 않는 기능 예외처리 - Status")
	void end() {
		State end = new End(ChessBoardFactory.create());
		assertThatThrownBy(end::end)
				.isInstanceOf(UnsupportedOperationException.class);
	}
}