package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class PawnTest {

	@ParameterizedTest
	@DisplayName("폰은 적팀을 향한 수직 또는 대각 방향으로 한 번 이동할 수 있다.")
	@MethodSource("chess.domain.piece.TestMethodSource#providePawnAvailableRelativePositionSource")
	void pawnCanMoveForwardAndForwardDiagonal1DistanceTest(int x, int y) {
		Piece whitePawn = new Pawn(Team.WHITE);
		Piece blackPawn = new Pawn(Team.BLACK);
		RelativePosition whiteRelativePosition = new RelativePosition(x, y);
		RelativePosition blackRelativePosition = new RelativePosition(x, y * -1);

		assertTrue(whitePawn.isMobile(whiteRelativePosition));
		assertTrue(blackPawn.isMobile(blackRelativePosition));
	}

	@ParameterizedTest
	@DisplayName("폰은 모든 방향으로 1을 초과하는 거리에 대한 이동이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideAllDirectionRelativePositionSource")
	void pawnCannotMoveMoreThan1DistanceTest(int x, int y) {
		Piece whitePawn = new Pawn(Team.WHITE);
		Piece blackPawn = new Pawn(Team.BLACK);

		for (int distance = 2; distance < 8 ; distance++) {
			RelativePosition whiteRelativePosition = new RelativePosition(x * distance, y * distance);
			RelativePosition blackRelativePosition = new RelativePosition(x * distance, y * -1 * distance);

			assertFalse(whitePawn.isMobile(whiteRelativePosition));
			assertFalse(blackPawn.isMobile(blackRelativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("폰은 적팀을 향한 수직 또는 대각 방향을 제외한 모든 방향으로 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#providePawnUnavailableRelativePositionSource")
	void pawnCannotMoveNoneForwardDirectionTest(int x, int y) {
		Piece whitePawn = new Pawn(Team.WHITE);
		Piece blackPawn = new Pawn(Team.BLACK);
		RelativePosition whiteRelativePosition = new RelativePosition(x, y);
		RelativePosition blackRelativePosition = new RelativePosition(x, y * -1);

		assertFalse(whitePawn.isMobile(whiteRelativePosition));
		assertFalse(blackPawn.isMobile(blackRelativePosition));
	}
}
