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

class KnightTest {

    @Test
    void 말이_움직일_수_있는_경로인지_확인할_수_있다() {
        ChessPiece knight = new Knight();
        Position origin = new Position(Row.FOUR, Column.D);
        Position destination = new Position(Row.FIVE, Column.F);

        assertDoesNotThrow(() -> knight.validateCanMove(origin, destination));
    }

    @Test
    void 말이_움직일_수_없는_경로인지_확인할_수_있다() {
        ChessPiece knight = new Knight();
        Position origin = new Position(Row.FOUR, Column.D);
        Position destination = new Position(Row.SIX, Column.F);

        assertThatThrownBy(() -> knight.validateCanMove(origin, destination));
    }

    @Test
    void 경로를_찾을_수_있다() {
        ChessPiece knight = new Knight();
        Position origin = new Position(Row.FOUR, Column.D);
        Position destination = new Position(Row.FIVE, Column.F);

        assertThat(knight.findRoute(origin, destination))
                .containsExactly(Movement.RIGHT_RIGHT_UP);
    }
}
