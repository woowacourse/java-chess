package chess.domain.piece;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("체스말을 이름을 갖고 있다")
    void name() {
        // given
        final var name = "R";
        final var piece = new TestPiece(name);

        // when
        final var actual = piece.name();

        // then
        assertThat(actual).isEqualTo(name);
    }
}

final class TestPiece extends Piece {

    public TestPiece(final String name) {
        super(name);
    }

    @Override
    public boolean movable(final Direction direction) {
        return false;
    }

    @Override
    public boolean movableByCount(final int count) {
        return false;
    }
}
