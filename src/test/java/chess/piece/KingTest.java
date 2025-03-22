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
    void moveUpOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        king.move(new Position(Column.E, Row.SIX), board);

        assertThat(king).isEqualTo(new King(Color.WHITE, new Position(Column.E, Row.SIX)));
    }

    @Test
    void moveLeftUpOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        king.move(new Position(Column.D, Row.SIX), board);

        assertThat(king).isEqualTo(new King(Color.WHITE, new Position(Column.D, Row.SIX)));
    }

    @Test
    void moveRightDownOnce() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        king.move(new Position(Column.F, Row.FOUR), board);

        assertThat(king).isEqualTo(new King(Color.WHITE, new Position(Column.F, Row.FOUR)));
    }

    @Test
    void failToMoveTwice() {
        King king = new King(Color.WHITE, new Position(Column.E, Row.FIVE));
        Board board = new Board(List.of(
                king
        ));

        assertThatThrownBy(() -> king.move(new Position(Column.E, Row.THREE), board))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
