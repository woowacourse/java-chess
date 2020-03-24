package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {
	@DisplayName("비숍 생성")
	@Test
	void constructor_CreateBishop_Success() {
		Assertions.assertThat(new Bishop()).isInstanceOf(Bishop.class);
	}
}
