package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.Color.BLACK;
import static chess.domain.File.A;
import static chess.domain.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PieceTest {

    @Test
    @DisplayName("위치를 가지는 말이 정상적으로 생성이 된다")
    void initTest() {
        assertThatNoException().isThrownBy(() -> new TestPiece(A, ONE, BLACK));
    }

    private final class TestPiece extends Piece {
        public TestPiece(final File file, final Rank rank, final Color color) {
            super(file, rank, color);
        }
    }

}
