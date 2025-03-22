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

class BishopTest {

    @Test
    void moveLeftUpTwice() {
        Bishop bishop = new Bishop(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                bishop
        ));
        Position newPosition = new Position(Column.C, Row.SEVEN);

        bishop.move(newPosition, board);

        assertThat(bishop).isEqualTo(new Bishop(Color.WHITE, newPosition));
    }

    @Test
    void failIfCardinalMove() {
        Bishop bishop = new Bishop(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                bishop
        ));
        Position newPosition = new Position(Column.C, Row.FIVE);

        assertThatThrownBy(() -> bishop.move(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failIfHurdleExists() {
        Bishop bishop = new Bishop(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                new Pawn(Color.BLACK, new Position(Column.D, Row.SIX)),
                bishop
        ));
        Position newPosition = new Position(Column.C, Row.SEVEN);

        assertThatThrownBy(() -> bishop.move(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
