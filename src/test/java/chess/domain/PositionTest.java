package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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

	private static Stream<Arguments> inBetweenSource() {
		return Stream.of(
			Arguments.of(new Position("a1"), new Position("c3"), Collections.singletonList(new Position("b2"))),
			Arguments.of(new Position("a2"), new Position("d2"),
				Arrays.asList(new Position("b2"), new Position("c2"))),
			Arguments.of(new Position("h7"), new Position("d7"),
				Arrays.asList(new Position("g7"), new Position("f7"), new Position("e7")))
		);
	}

	@ParameterizedTest
	@DisplayName("Source, Destination 사이의 방향과 거리가 맞는지 확인")
	@MethodSource("inBetweenSource")
	void inBetweenTest(Position source, Position destination, List<Position> positions) {
		assertThat(source.getPositionsInBetween(destination)).containsAll(positions);

	}
}
