package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.Path;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.PositionFixture.A1;
import static chess.PositionFixture.C8;
import static chess.domain.piece.property.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PieceTest {

    private static final class TestPiece extends Piece {

        public TestPiece(final Position position, Color color) {
            super(position, color);
        }

        @Override
        protected boolean canMove(final Position targetPosition) {
            return false;
        }

        @Override
        public Piece move(final Piece pieceInTargetPosition) {
            return null;
        }

        @Override
        public Path getPassingPositions(final Position targetPosition) {
            return null;
        }

    }

    @Test
    @DisplayName("위치를 가지는 말이 정상적으로 생성이 된다")
    void init_test() {
        assertThatNoException().isThrownBy(() -> new TestPiece(A1, BLACK));
    }

    @ParameterizedTest()
    @DisplayName("같은 색인지 확인한다")
    @CsvSource({"BLACK, true", "WHITE, false"})
    void check_is_same_color_test(final Color otherColor, final boolean expected) {
        final Piece piece = new TestPiece(C8, BLACK);

        final boolean actual = piece.isSameColor(otherColor);

        assertThat(actual).isEqualTo(expected);
    }
}
