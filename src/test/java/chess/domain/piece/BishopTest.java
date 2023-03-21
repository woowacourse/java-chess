package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class BishopTest {

	@ParameterizedTest
	@DisplayName("비숍은 팀과 거리에 관계 없이 대각선 방향으로 이동이 가능하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideDiagonalRelativePositionSource")
	void bishopCanMoveDiagonalDirectionTest(int x, int y) {
		Piece whiteBishop = new Bishop(Team.WHITE);
		Piece blackBishop = new Bishop(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);

			assertTrue(whiteBishop.isMobile(relativePosition));
			assertTrue(blackBishop.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("비숍은 팀과 거리에 관계 없이 십자가 방향으로 이동이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideCrossRelativePositionSource")
	void bishopCannotMoveCrossDirectionTest(int x, int y) {
		Piece whiteBishop = new Bishop(Team.WHITE);
		Piece blackBishop = new Bishop(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);

			assertFalse(whiteBishop.isMobile(relativePosition));
			assertFalse(blackBishop.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("비숍은 팀과 거리에 관계 없이 L자 방향으로 이동이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideLShapedRelativePositionSource")
	void bishopCannotMoveLShapeDirectionTest(int x, int y) {
		Piece whiteBishop = new Bishop(Team.WHITE);
		Piece blackBishop = new Bishop(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);

			assertFalse(whiteBishop.isMobile(relativePosition));
			assertFalse(blackBishop.isMobile(relativePosition));
		}
	}
}
