package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class KingTest {

	@ParameterizedTest
	@DisplayName("킹은 팀에 관계 없이 십자와 대각 방향으로의 한 칸 움직임이 가능하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideCrossAndDiagonalRelativePositionSource")
	void kingCanMoveCrossAndDiagonalDirectionTest(int x, int y) {
		Piece whiteKing = new King(Team.WHITE);
		Piece blackKing = new King(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteKing.isMobile(relativePosition));
		assertTrue(blackKing.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("킹은 팀에 관계 없이 L자 방향으로의 한 칸 움직임이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideLShapedRelativePositionSource")
	void kingCannotMoveLShapedDirectionTest(int x, int y) {
		Piece whiteKing = new King(Team.WHITE);
		Piece blackKing = new King(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertFalse(whiteKing.isMobile(relativePosition));
		assertFalse(blackKing.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("킹은 팀과 관계 없이 어떤 방향으로든 1칸 초과하여 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideAllDirectionRelativePositionSource")
	void kingCannotMoveMoreThan1Test(int x, int y) {
		King whiteKing = new King(Team.WHITE);
		King blackKing = new King(Team.BLACK);

		for (int distance = 2; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(whiteKing.isMobile(relativePosition));
			assertFalse(blackKing.isMobile(relativePosition));
		}
	}

	@Test
	@DisplayName("king 이라는 타입을 반환해야 한다.")
	void getTypeTest() {
		Piece king = new King(Team.WHITE);
		assertThat(king.getType()).isEqualTo(PieceType.KING);
	}
}
