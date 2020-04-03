package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;

public class PositionTest {
	@DisplayName("of 메서드에 대해서 File과 Rank가 같을 때 하나의 Position 인스턴스만 생성되는지 확인")
	@Test
	void ofTest() {
		assertThat(Position.of("a1")).isEqualTo(Position.of("a1"));
		assertThat(Position.of(chess.domain.board.Column.A, Row.ONE)).isEqualTo(Position.of("a1"));
		assertThat(Position.of(8, 1)).isEqualTo(Position.of("a1"));
	}

	@DisplayName("of 메서드에 대해서 체스판 범위 밖의 값을 입력했을 때 예외처리")
	@Test
	void ofExceptionTest() {
		assertThatThrownBy(() -> {
			Position.of("a9");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void isInitialPawnPosition() {
		assertThat(Position.of("a2").isInitialPawnPosition(Color.WHITE)).isTrue();
		assertThat(Position.of("b7").isInitialPawnPosition(Color.BLACK)).isTrue();
	}
}
