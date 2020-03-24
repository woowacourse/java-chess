package chess.domain;

import static org.assertj.core.api.Assertions.*;

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
}
