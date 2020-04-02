package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ColorTest {
	@CsvSource(value = {"BLACK,WHITE", "WHITE,BLACK"})
	@ParameterizedTest
	void reverse(Color color, Color expect) {
		assertThat(color.reverse()).isEqualTo(expect);
	}
}
