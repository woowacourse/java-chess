package chess.domain.movement;

import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovementTest {

	@Nested
	@DisplayName("킹 isMobile 테스트")
	class kingTest {

		@ParameterizedTest
		@DisplayName("방향과 관계없이 한 칸만 움직임이 가능하다.")
		@CsvSource({"0,1","1,1","1,0","1,-1","0,-1","-1,-1","-1,0","-1,1"})
		void kingValidMobilityTest(int x, int y) {
			Movement kingMovement = Movement.KING;
			RelativePosition relativePosition = new RelativePosition(x,y);

			assertTrue(kingMovement.isMobile(relativePosition));
		}

		@Test
		@DisplayName("두 칸 이상의 움직임에 대해선 거짓을 반환한다.")
		void kingInvalidMobilityTest() {
			Movement kingMovement = Movement.KING;
			RelativePosition relativePosition = new RelativePosition(2,0);

			assertFalse(kingMovement.isMobile(relativePosition));
		}
	}

	@Nested
	@DisplayName("폰 isMobile 테스트")
	class pawnTest {

		@Test
		@DisplayName("위로 한 칸 전진할 수 있다.")
		void pawnValidMobilityTest() {
			Movement pawnMovement = Movement.PAWN;
			RelativePosition relativePosition = new RelativePosition(0,1);

			assertTrue(pawnMovement.isMobile(relativePosition));
		}

		@Test
		@DisplayName("위로 한 칸이 아닌 움직임에 대해선 거짓을 반환한다.")
		void pawnInvalidMobilityTest() {
			Movement pawnMovement = Movement.PAWN;
			RelativePosition relativePosition = new RelativePosition(0,2);

			assertFalse(pawnMovement.isMobile(relativePosition));
		}
	}
}
