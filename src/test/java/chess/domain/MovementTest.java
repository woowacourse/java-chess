package chess.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MovementTest {

	@Nested
	@DisplayName("킹 isMobile 테스트")
	class kingTest {
		@Test
		@DisplayName("방향과 관계없이 한 칸만 움직인다.")
		void kingValidMobilityTest() {
			Movement pawnMovement = Movement.PAWN;
			RelativePosition relativePosition = new RelativePosition(1,1);

			assertFalse(pawnMovement.isMobile(relativePosition));
		}

		@Test
		@DisplayName("두 칸 이상의 움직임에 대해선 거짓을 반환한다.")
		void kingInvalidMobilityTest() {
			Movement pawnMovement = Movement.PAWN;
			RelativePosition relativePosition = new RelativePosition(2,0);

			assertFalse(pawnMovement.isMobile(relativePosition));
		}
	}

	//todo : 테스트 케이스 추가

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
