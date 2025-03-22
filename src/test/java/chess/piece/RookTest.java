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

class RookTest {
    @Test
    void moveToUpTwice() {
        Rook rook = new Rook(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                rook
        ));
        Position newPosition = new Position(Column.E, Row.SEVEN);

        rook.moveTo(newPosition, board);

        assertThat(rook).isEqualTo(new Rook(Color.WHITE, newPosition));
    }

    @Test
    void moveToRightTwice() {
        Rook rook = new Rook(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                rook
        ));
        Position newPosition = new Position(Column.G, Row.FIVE);

        rook.moveTo(newPosition, board);

        assertThat(rook).isEqualTo(new Rook(Color.WHITE, newPosition));
    }

    @Test
    void failIfDiagonalMoveTo() {
        Rook rook = new Rook(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                rook
        ));
        Position newPosition = new Position(Column.D, Row.FOUR);

        assertThatThrownBy(() -> rook.moveTo(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failIfHurdleExists() {
        Rook rook = new Rook(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                new Pawn(Color.BLACK, new Position(Column.E, Row.SIX)),
                rook
        ));
        Position newPosition = new Position(Column.E, Row.SEVEN);

        assertThatThrownBy(() -> rook.moveTo(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failIfNotStraight() {
        Rook rook = new Rook(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                rook
        ));
        Position newPosition = new Position(Column.F, Row.SEVEN);

        assertThatThrownBy(() -> rook.moveTo(newPosition, board))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
