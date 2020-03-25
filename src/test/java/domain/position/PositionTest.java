package domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
	@DisplayName("위치를 입력하면 column과 row 초기화 ")
	@Test
	void constructor_StringPosition_InitializeXAndY() {
		Position b3 = new Position("b3");

		assertThat(b3.getColumn()).isEqualTo(Column.B);
		assertThat(b3.getRow()).isEqualTo(3);
	}
}