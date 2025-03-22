package chess.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Column;
import chess.Position;
import chess.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    void 킹_움직임_테스트() {
        // given
        Position fromPosition = new Position(Column.D, Row.SIX);
        Position toPosition = new Position(Column.D, Row.SEVEN);
        King king = new King();
        king.canMove(fromPosition, toPosition);

        Assertions.assertDoesNotThrow(() -> king.canMove(fromPosition, toPosition));

    }

    @Test
    void 킹_움직임_테스트2() {
        // given
        Position fromPosition = new Position(Column.D, Row.SIX);
        Position toPosition = new Position(Column.D, Row.EIGHT);
        King king = new King();

        assertThatThrownBy(() -> king.canMove(fromPosition, toPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("애초에 니 못감 ㅅㄱㅇ");

    }

}