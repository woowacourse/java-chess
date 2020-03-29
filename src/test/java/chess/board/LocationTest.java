package chess.board;

import static org.assertj.core.api.Assertions.*;

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

}