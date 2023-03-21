package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class QueenTest {

	@ParameterizedTest
	@DisplayName("퀸은 거리와 팀과 관계 없이 십자와 대각 방향으로 이동할 수 있다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideCrossAndDiagonalRelativePositionSource")
	void queenCanMoveCrossAndDiagonalDirectionTest(int x, int y) {
		Piece whiteQueen = new Queen(Team.WHITE);
		Piece blackQueen = new Queen(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertTrue(whiteQueen.isMobile(relativePosition));
			assertTrue(blackQueen.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("퀸은 거리와 팀과 관계 L자 방향으로 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideLShapedRelativePositionSource")
	void queenCannotMoveLShapedDirectionTest(int x, int y) {
		Piece whiteQueen = new Queen(Team.WHITE);
		Piece blackQueen = new Queen(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(whiteQueen.isMobile(relativePosition));
			assertFalse(blackQueen.isMobile(relativePosition));
		}
	}
}
