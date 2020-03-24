package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {
	@DisplayName("룩 생성")
	@Test
	void constructor_CreateKnight_Success() {
		Assertions.assertThat(new Rook()).isInstanceOf(Rook.class);
	}
}
