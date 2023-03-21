package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

	@ParameterizedTest
	@DisplayName("위치가 (0,0)에서 (7,7) 사이면 정상적으로 생성된다.")
	@CsvSource({"0,0", "0,7", "7,0", "7,7"})
	void validPositionTest(int column, int row){
		assertDoesNotThrow(() -> new Position(column, row));
	}

	@ParameterizedTest
	@DisplayName("위치가 (0,0)에서 (7,7) 사이가 아니면 예외처리한다.")
	@CsvSource({"-1,0", "0,-1", "8,0", "7,8"})
	void invalidPositionTest(int column, int row){
		assertThrows(IllegalArgumentException.class, () -> new Position(column, row));
	}

	@Test
	@DisplayName("위치에서 상대 위치로 이동 성공 테스트")
	void movePositionSuccessTest() {
		Position initPosition = new Position(3, 5);
		RelativePosition relativePosition = new RelativePosition(-1, 2);

		Position newPosition = initPosition.move(relativePosition);
		assertThat(newPosition.getColumn()).isEqualTo(2);
		assertThat(newPosition.getRow()).isEqualTo(7);
	}

	@Test
	@DisplayName("위치에서 최대 좌표를 넘어가는 상대 위치로 이동 실패 테스트")
	void movePositionFailTest() {
		Position initPosition = new Position(3, 5);
		RelativePosition relativePosition = new RelativePosition(3, 3);

		assertThrows(IllegalArgumentException.class,
			() -> initPosition.move(relativePosition));
	}
}
