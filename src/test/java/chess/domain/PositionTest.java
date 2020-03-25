package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {
	@Test
	@DisplayName("위치 정보를 String으로 받아 좌표값으로 변환")
	void PositionReceive() {
		assertThat(new Position("e1")).isEqualTo(new Position(5, 1));
	}

	@ParameterizedTest
	@DisplayName("잘못된 위치 값을 받은 경우 예외를 잘 처리하는지 확인")
	@CsvSource(value = {"0,4", "9,2", "1,0", "3,9"})
	void invalidPositionTest(int x, int y) {
		assertThatThrownBy(() ->
			new Position(x, y)
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("체스판 범위");

	}

	@ParameterizedTest
	@DisplayName("Source, Destination 사이의 방향과 거리가 맞는지 확인")
	@CsvSource(value = {"a1,c3,NORTHEAST,2", "a2,e2,EAST,4", "b4,e1,SOUTHEAST,3", "d5,d1,SOUTH,4", "h6,e3,SOUTHWEST,3",
		"h7,d7,WEST,4", "g4,d7,NORTHWEST,3", "c1,c6,NORTH,5"})
	void inBetweenTest(String source, String destination, Direction direction, int distance) {
		Map<Direction, Integer> between = new Position(source).inBetween(new Position(destination));
		assertThat(between.containsKey(direction) && between.get(direction) == distance).isTrue();
	}
}
