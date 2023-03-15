package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.RelativePosition;
import chess.domain.Team;

class PawnTest {

	@Test
	@DisplayName("폰은 처음에 앞으로 2칸 갈 수 있다.")
	void pawnIsMobileTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -2);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("폰은 처음에 앞으로 1칸만 갈 수도 있다.")
	void pawnIsMobileTest2() {
		Pawn pawn = new Pawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(0, 1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("폰은 이미 움직인 적이 있다면 앞으로 2칸 갈 수 없다.")
	void pawnIsMobileTest3() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -2);

		pawn.isMobile(relativePosition);
		assertFalse(pawn.isMobile(relativePosition));
	}
}
