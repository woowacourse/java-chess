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

class QueenTest {
    @Test
    void moveUpTwice() {
        Queen queen = new Queen(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                queen
        ));
        Position newPosition = new Position(Column.E, Row.SEVEN);

        queen.move(newPosition, board);

        assertThat(queen).isEqualTo(new Queen(Color.WHITE, newPosition));
    }

    @Test
    void failIfHurdleExists() {
        Queen queen = new Queen(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                new Pawn(Color.BLACK, new Position(Column.E, Row.SIX)),
                queen
        ));
        Position newPosition = new Position(Column.E, Row.SEVEN);

        assertThatThrownBy(() -> queen.move(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
