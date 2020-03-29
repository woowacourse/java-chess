package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Direction;

public class PositionTest {
	@DisplayName("File과 Rank가 같을 때 하나의 Position 인스턴스만 생성되는지 확인")
	@Test
	void newTest() {
		assertThat(Position.of("a1")).isEqualTo(Position.of("a1"));
	}

	@DisplayName("잘못된 포지션으로 생성하려할 때 예외처리")
	@Test
	void of() {
		assertThatThrownBy(() -> Position.of("q1"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("유효하지");
	}

	@DisplayName("방향이 주어졌을때 가능한 포지션 리스트를 반환하는지 확인")
	@Test
	void nextPosition() {
		Position position = Position.of("a1");

		List<Direction> direction = Direction.everyDirection();

		assertThat(position.nextPosition(direction).size()).isEqualTo(3);
		assertThat(position.nextPosition(direction).contains(Position.of("a2"))).isTrue();
		assertThat(position.nextPosition(direction).contains(Position.of("b2"))).isTrue();
		assertThat(position.nextPosition(direction).contains(Position.of("b1"))).isTrue();
	}
}
