package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class KnightTest {

	@ParameterizedTest
	@DisplayName("나이트는 팀과 관계 없이 L자 모양의 모든 방향으로 한 번 이동할 수 있다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideLShapedRelativePositionSource")
	void knightCanMoveEveryLShapedDirectionTest(int x, int y) {
		Piece whiteKnight = new Knight(Team.WHITE);
		Piece blackKnight = new Knight(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteKnight.isMobile(relativePosition));
		assertTrue(blackKnight.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("나이트는 팀과 관계 없이 L자 모양의 모든 방향으로 두 번 이상 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideLShapedRelativePositionSource")
	void knightCannotMoveEveryLShapedDirectionMoreThanOnceTest(int x, int y) {
		Piece whiteKnight = new Knight(Team.WHITE);
		Piece blackKnight = new Knight(Team.BLACK);

		for (int distance = 2; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(whiteKnight.isMobile(relativePosition));
			assertFalse(blackKnight.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("나이트는 거리와 팀과 관계 없이 십자와 대각 방향으로 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideCrossAndDiagonalRelativePositionSource")
	void knightCannotMoveCrossAndDiagonalTest(int x, int y) {
		Piece whiteKnight = new Knight(Team.WHITE);
		Piece blackKnight = new Knight(Team.BLACK);

		for (int distance = 2; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(whiteKnight.isMobile(relativePosition));
			assertFalse(blackKnight.isMobile(relativePosition));
		}
	}
}
