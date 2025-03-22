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

public class PawnTest {

    private Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.TWO));
    private Board board = new Board(List.of(
            pawn
    ));

    @Test
    void moveUpOnce() {
        pawn.moveUp(new Position(Column.A, Row.THREE), board);

        assertThat(pawn).isEqualTo(new Pawn(Color.WHITE, new Position(Column.A, Row.THREE)));
    }

    @Test
    void failToMoveUpMoreThanOnce() {
        assertThatThrownBy(() -> pawn.moveUp(new Position(Column.A, Row.FIVE), board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveUpTwiceIfStartMoving() {
        pawn.moveUp(new Position(Column.A, Row.FOUR), board);

        assertThat(pawn).isEqualTo(new Pawn(Color.WHITE, new Position(Column.A, Row.FOUR)));
    }

    @Test
    void failIfHurdleExists() {
        Board hurdleBoard = new Board(List.of(
                new Pawn(Color.BLACK, new Position(Column.A, Row.THREE)),
                pawn
        ));

        assertThatThrownBy(() -> pawn.moveUp(new Position(Column.A, Row.FOUR), hurdleBoard))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
