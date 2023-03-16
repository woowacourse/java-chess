package chess.domain;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.File.A;
import static chess.domain.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PieceTest {

    private static final class TestPiece extends Piece {
        public TestPiece(final File file, final Rank rank, final Color color) {
            super(file, rank, color);
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
        public List<Position> getPassingPositions(final Position targetPosition) {
            return null;
        }

    }

    @Test
    @DisplayName("위치를 가지는 말이 정상적으로 생성이 된다")
    void initTest() {
        assertThatNoException().isThrownBy(() -> new TestPiece(A, ONE, BLACK));
    }

    @ParameterizedTest()
    @DisplayName("같은 색인지 확인한다")
    @CsvSource({"BLACK, true", "WHITE, false"})
    void isSameColorTest(final Color otherColor, final boolean expected) {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, BLACK);

        final boolean actual = piece.isSameColor(otherColor);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("검정 색인지 확인한다")
    @CsvSource({"BLACK, true", "WHITE, false"})
    void isBlackTest(final Color color, final boolean expected) {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, color);

        final boolean actual = piece.isBlack();

        assertThat(actual).isEqualTo(expected);
    }
}
