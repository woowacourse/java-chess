package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.RelativePosition;
import chess.domain.Team;

class InitialPawnTest {

	@Test
	@DisplayName("흑팀 폰은 처음에 아래로 2칸 전진할 수 있다.")
	void blackPawnCanMoveSouth2FirstTest() {
		InitialPawn pawn = new InitialPawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -2);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("흑팀 폰은 처음에 아래로 1칸만 전진할 수도 있다.")
	void blackPawnCanMoveSouth1FirstTest() {
		InitialPawn pawn = new InitialPawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(0, -1);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("백팀 폰은 처음 위로 2칸 전진할 수 있다.")
	void blackPawnCanMoveNorth2FirstTest() {
		InitialPawn pawn = new InitialPawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(0, 2);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@Test
	@DisplayName("흑팀 폰은 처음에 아래로 1칸만 전진할 수도 있다.")
	void blackPawnCanMoveNorth1FirstTest() {
		InitialPawn pawn = new InitialPawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(0, 1);

		assertTrue(pawn.isMobile(relativePosition));
	}

}
