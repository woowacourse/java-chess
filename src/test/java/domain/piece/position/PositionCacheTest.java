package domain.piece.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionCacheTest {
	@DisplayName("위치를 입력하면 캐싱된 포지션 반환")
	@Test
	void of_ValidPosition_ReturnPosition() {
		Position b3 = PositionCache.of("b3");
		assertThat(b3.getColumn()).isEqualTo(Column.B);
		assertThat(b3.getRowNumber()).isEqualTo(3);
	}

	@DisplayName("잘못된 위치를 입력하면 예외 발생")
	@Test
	void of_InvalidPosition_ExceptionThrown() {
		assertThatThrownBy(() -> PositionCache.of("z3"))
			.isInstanceOf(NullPointerException.class)
			.hasMessage(InvalidPositionException.INVALID_BOUNDARY_POSITION);
	}
}
