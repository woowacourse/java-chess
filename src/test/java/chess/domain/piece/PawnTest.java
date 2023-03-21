package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

	@Test
	@DisplayName("흑팀 폰은 처음 남쪽으로 2칸 전진할 수 있다.")
	void blackPawnCanMoveSouth2FirstTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -2);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("흑팀 폰은 처음에 아래로 1칸만 전진할 수도 있다.")
	void blackPawnCanMoveSouth1FirstTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("백팀 폰은 처음 위로로 2칸 전진할 수 있다.")
	void blackPawnCanMoveNorth2FirstTest() {
		Pawn pawn = new Pawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(0, 2);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("흑팀 폰은 처음에 아래로 1칸만 전진할 수도 있다.")
	void blackPawnCanMoveNort1FirstTest() {
		Pawn pawn = new Pawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(0, 1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("폰은 이미 움직인 적이 있다면 앞으로 2칸 갈 수 없다.")
	void whitePawnCannotMoveSouth2AgainTest() {
		Pawn pawn = new Pawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -2);

		pawn.isMobile(relativePosition);
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
