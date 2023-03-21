package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.RelativePosition;

class EmptyTest {

	@ParameterizedTest
	@DisplayName("빈 기물은 모든 방향으로의 움직임이 불가하다.")
	@MethodSource("chess.domain.piece.TestMethodSource#provideAllDirectionRelativePositionSource")
	void kingCanMoveEveryDirectionTest(int x, int y) {
		Piece empty = new Empty();
		RelativePosition relativePosition = new RelativePosition(x, y);

		Exception e = assertThrows(UnsupportedOperationException.class,
			() -> empty.isMobile(relativePosition));
		assertEquals("이동이 불가능한 기물입니다.", e.getMessage());
	}
}
