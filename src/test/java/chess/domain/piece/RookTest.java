package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Column;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    void 룩이_움직일_수_있는_경로인지_확인할_수_있다() {
        ChessPiece rook = new Rook();
        Position origin = new Position(Row.FOUR, Column.B);
        Position destination = new Position(Row.FOUR, Column.G);

        assertDoesNotThrow(() -> rook.validateCanMove(origin, destination));
    }

    @Test
    void 룩이_움직일_수_없는_경로인지_확인할_수_있다() {
        ChessPiece rook = new Rook();
        Position origin = new Position(Row.FOUR, Column.B);
        Position destination = new Position(Row.SIX, Column.F);

        assertThatThrownBy(() -> rook.validateCanMove(origin, destination))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 경로를_찾을_수_있다() {
        ChessPiece rook = new Rook();
        Position origin = new Position(Row.FOUR, Column.B);
        Position destination = new Position(Row.FOUR, Column.G);

        assertThat(rook.findRoute(origin, destination))
                .containsExactly(Movement.RIGHT, Movement.RIGHT, Movement.RIGHT, Movement.RIGHT, Movement.RIGHT);
    }
}
