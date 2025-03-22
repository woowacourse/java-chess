package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.MoveStrategy.SpecialMoveStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍 이동 가능 테스트")
    @Test
    void bishopCanMoveTest() {

        // given
        Piece bishop = new Bishop(Color.BLACK, new Position(Row.ONE, Column.A));
        Position endPosition = new Position(Row.EIGHT, Column.H);

        // when
        List<Position> result = bishop.canMove(endPosition);

        // then
        assertThat(result.size()).isEqualTo(7);
    }

    @DisplayName("비숍 이동 불가능 테스트")
    @Test
    void bishopCantMoveTest() {

        // given
        Piece bishop = new Bishop(Color.BLACK, new Position(Row.ONE, Column.A), new SpecialMoveStrategy());
        Position endPosition = new Position(Row.EIGHT, Column.F);

        // when

        // then
        assertThatThrownBy(() -> bishop.canMove(endPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
