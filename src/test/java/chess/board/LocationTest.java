package chess.board;

import static org.assertj.core.api.Assertions.*;

import chess.location.Location;
import chess.location.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

	@DisplayName("로케이션 절대위치 이동")
	@Test
	void moveTo() {
		Location location = new Location(1, 'a');
		Location actual = location.moveTo(2, 'c');

		Location expect = new Location(2, 'c');

		assertThat(actual).isEqualTo(expect);
	}

	@DisplayName("로케이션 비교")
	@Test
	void isSameTo() {
		Location location = new Location(1, 'a');
		Row row = Row.of(1);
		assertThat(location.isSameRow(row)).isTrue();
	}

	@DisplayName("로케이션 비교 실패")
	@Test
	void isSameTo2() {
		Location location = new Location(1, 'a');
		Row row = Row.of(2);
		assertThat(location.isSameRow(row)).isFalse();
	}
}