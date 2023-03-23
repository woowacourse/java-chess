package chess.piece;

import chess.board.Position;
import chess.fixture.FixturePosition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EmptyPieceTest {

    @Test
    void 빈_말에_대해_메소드를_수행하면_예외() {
        //given
        EmptyPiece emptyPiece = new EmptyPiece();

        Position to = FixturePosition.B3;
        Position from = FixturePosition.A1;

        //when & then
        Assertions.assertThatThrownBy(() -> emptyPiece.isMovable(from, to, PieceFixture.EMPTY_PIECE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 말은 움직일 수 없습니다.");
    }

}
