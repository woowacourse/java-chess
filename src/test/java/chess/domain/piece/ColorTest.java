package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColorTest {
	@Test
	@DisplayName("컬러가 화이트인지 확인")
	void isWhite() {
		assertThat(Color.WHITE.isWhite()).isTrue();
	}

	@Test
	@DisplayName("컬러가 블랙인지 확인")
	void isBlack() {
		assertThat(Color.BLACK.isBlack()).isTrue();
	}

	@Test
	@DisplayName("컬러가 없는지 확인")
	void isNone() {
		assertThat(Color.NONE.isNone()).isTrue();
	}
}
