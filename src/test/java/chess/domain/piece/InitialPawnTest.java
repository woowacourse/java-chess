package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class InitialPawnTest {

	@ParameterizedTest
	@DisplayName("흑팀 폰은 처음에 아래로 1칸 또는 2칸 전진할 수 있다.")
	@CsvSource({"0,-1", "0,-2"})
	void initialBlackPawnCanMoveSouth1Or2Test(int x, int y) {
		Piece pawn = new InitialPawn(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("백팀 폰은 처음 위로 1칸 또는 2칸 전진할 수 있다.")
	@CsvSource({"0,1", "0,2"})
	void initialWhitePawnCanMoveNorth1Or2Test(int x, int y) {
		Piece pawn = new InitialPawn(Team.WHITE);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(pawn.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("흑팀 폰은 아래를 제외한 모든 방향으로 이동이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideAllDirectionRelativePositionSource")
	void initialBlackPawnCannotMoveNotSouthTest(int x, int y) {
		Piece pawn = new InitialPawn(Team.BLACK);
		if (x == 0 && y == -1) {
			return;
		}

		for (int distance = 1; distance < 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(pawn.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("백팀 폰은 아래를 제외한 모든 방향으로 이동이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideAllDirectionRelativePositionSource")
	void initialWhitePawnCannotMoveNotSouthTest(int x, int y) {
		Piece pawn = new InitialPawn(Team.WHITE);
		if (x == 0 && y == 1) {
			return;
		}

		for (int distance = 1; distance < 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(pawn.isMobile(relativePosition));
		}
	}
}
