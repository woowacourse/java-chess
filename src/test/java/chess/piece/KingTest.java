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

class KingTest {
    @Test
    void moveToUpOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        king.moveTo(new Position(Column.E, Row.SIX), board);

        assertThat(king).isEqualTo(new King(Color.WHITE, new Position(Column.E, Row.SIX)));
    }

    @Test
    void moveToRightOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));
        Position newPosition = new Position(Column.F, Row.FIVE);

        king.moveTo(newPosition, board);

        assertThat(king).isEqualTo(new King(Color.WHITE, newPosition));
    }

    @Test
    void moveToLeftUpOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        king.moveTo(new Position(Column.D, Row.SIX), board);

        assertThat(king).isEqualTo(new King(Color.WHITE, new Position(Column.D, Row.SIX)));
    }

    @Test
    void moveToRightDownOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        king.moveTo(new Position(Column.F, Row.FOUR), board);

        assertThat(king).isEqualTo(new King(Color.WHITE, new Position(Column.F, Row.FOUR)));
    }

    @Test
    void failToMoveToTwice() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        assertThatThrownBy(() -> king.moveTo(new Position(Column.E, Row.THREE), board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failIfNotStraight() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        assertThatThrownBy(() -> king.moveTo(new Position(Column.D, Row.THREE), board))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
