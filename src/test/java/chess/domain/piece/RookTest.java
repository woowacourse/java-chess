package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class RookTest {

	@ParameterizedTest
	@DisplayName("룩은 팀과 관계 없이 십자 방향으로 거리 제한 없이 이동할 수 있다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideCrossRelativePositionSource")
	void rookCanMoveCrossDirectionTest(int x, int y) {
		Rook whiteRook = new Rook(Team.WHITE);
		Rook blackRook = new Rook(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertTrue(whiteRook.isMobile(relativePosition));
			assertTrue(blackRook.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("룩은 거리와 팀과 관계 없이 대각 방향으로 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideDiagonalRelativePositionSource")
	void knightCannotMoveDiagonalTest(int x, int y) {
		Piece whiteRook = new Rook(Team.WHITE);
		Piece blackRook = new Rook(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(whiteRook.isMobile(relativePosition));
			assertFalse(blackRook.isMobile(relativePosition));
		}
	}

	@ParameterizedTest
	@DisplayName("룩은 거리와 팀과 관계없이 L자 방향으로 이동할 수 없다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideLShapedRelativePositionSource")
	void rookCannotMoveLShapedDirectionTest(int x, int y) {
		Piece whiteRook = new Rook(Team.WHITE);
		Piece blackRook = new Rook(Team.BLACK);

		for (int distance = 1; distance <= 8; distance++) {
			RelativePosition relativePosition = new RelativePosition(x * distance, y * distance);
			assertFalse(whiteRook.isMobile(relativePosition));
			assertFalse(blackRook.isMobile(relativePosition));
		}
	}

	@Test
	@DisplayName("Rook이라는 타입을 반환해야 한다.")
	void getTypeTest() {
		Piece rook = new Rook(Team.WHITE);
		assertThat(rook.getType()).isEqualTo(PieceType.ROOK);
	}
}
