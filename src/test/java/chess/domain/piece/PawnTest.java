package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.RelativePosition;
import chess.domain.Team;

class PawnTest {

	@Test
	@DisplayName("흑팀의 일반 폰은 아래로 2칸 갈 수 없다.")
	void blackPawnCannotMoveSouth2AgainTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -2);

		assertFalse(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("백팀의 일반 폰은 아래로 2칸 갈 수 없다.")
	void whitePawnCannotMoveNorth2AgainTest() {
		Pawn pawn = new Pawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(0, 2);

		assertFalse(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("흑팀 폰은 남동쪽으로 1칸 이동할 수 있다.")
	void blackPawnCanMoveSouthEastTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(1, -1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("흑팀 폰은 남서쪽으로 1칸 이동할 수 있다.")
	void blackPawnCanMoveSouthWestTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(-1, -1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("백팀 폰은 북동쪽으로 1칸 이동할 수 있다.")
	void whitePawnCanMoveNorthEastTest() {
		Pawn pawn = new Pawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(1, 1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("백팀 폰은 북서쪽으로 1칸 이동할 수 있다.")
	void whitePawnCanMoveNorthWestTest() {
		Pawn pawn = new Pawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(-1, 1);

		assertTrue(pawn.isMobile(relativePosition));
	}
}
