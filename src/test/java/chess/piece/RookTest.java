package chess.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Column;
import chess.Position;
import chess.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RookTest {
    @Test
    void 룩_움직임_테스트() {
        // given
        Position fromPosition = new Position(Column.D, Row.SIX);
        Position toPosition = new Position(Column.D, Row.EIGHT);
        Position fromPosition2 = new Position(Column.D, Row.SIX);
        Position toPosition2 = new Position(Column.A, Row.SIX);
        Rook rook = new Rook();
        rook.canMove(fromPosition, toPosition);

        Assertions.assertDoesNotThrow(() -> rook.canMove(fromPosition, toPosition));
        Assertions.assertDoesNotThrow(() -> rook.canMove(fromPosition2, toPosition2));


    }

    @Test
    void 룩_움직임_테스트2() {
        // given
        Position fromPosition = new Position(Column.D, Row.SIX);
        Position toPosition = new Position(Column.C, Row.FIVE);

        Rook rook = new Rook();

        assertThatThrownBy(() -> rook.canMove(fromPosition, toPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("애초에 니 못감 ㅅㄱㅇ");

    }
}