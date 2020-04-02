package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

	@DisplayName("반대의 컬러를 반환하는지 확인하는 테스트")
	@Test
	void reverseTest() {
		assertThat(Color.WHITE.reverse()).isEqualTo(Color.BLACK);
		assertThat(Color.BLACK.reverse()).isEqualTo(Color.WHITE);
	}
}