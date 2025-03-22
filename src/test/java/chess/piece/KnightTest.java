package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.Test;

class KnightTest {
    @Test
    void moveUpUpLeftOnce() {
        Knight knight = new Knight(Color.WHITE, new Position(Column.D, Row.FIVE));
        Board board = new Board(List.of(
                knight
        ));
        Position newPosition = new Position(Column.C, Row.SEVEN);

        knight.move(newPosition, board);

        assertThat(knight).isEqualTo(new Knight(Color.WHITE, newPosition));
    }

    @Test
    void moveRightRightDownOnce() {
        Knight knight = new Knight(Color.WHITE, new Position(Column.D, Row.FIVE));
        Board board = new Board(List.of(
                knight
        ));
        Position newPosition = new Position(Column.F, Row.FOUR);


        knight.move(newPosition, board);

        assertThat(knight).isEqualTo(new Knight(Color.WHITE, newPosition));
    }

    @Test
    void failToMoveOther() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));
        Position newPosition = new Position(Column.F, Row.THREE);

        assertThatThrownBy(() -> king.move(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
