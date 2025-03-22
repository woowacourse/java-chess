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

    @Test
    void moveToUpOnce() {
        Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.TWO));
        Board board = new Board(List.of(
                pawn
        ));

        pawn.moveTo(new Position(Column.A, Row.THREE), board);

        assertThat(pawn).isEqualTo(new Pawn(Color.WHITE, new Position(Column.A, Row.THREE)));
    }

    @Test
    void moveToDownOnce() {
        Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.THREE));
        Board board = new Board(List.of(
                pawn
        ));

        pawn.moveTo(new Position(Column.A, Row.TWO), board);

        assertThat(pawn).isEqualTo(new Pawn(Color.WHITE, new Position(Column.A, Row.TWO)));
    }

    @Test
    void failToMoveToUpMoreThanOnce() {
        Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.TWO));
        Board board = new Board(List.of(
                pawn
        ));

        assertThatThrownBy(() -> pawn.moveTo(new Position(Column.A, Row.FIVE), board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveToUpTwiceIfStartMoving() {
        Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.TWO));
        Board board = new Board(List.of(
                pawn
        ));

        pawn.moveTo(new Position(Column.A, Row.FOUR), board);

        assertThat(pawn).isEqualTo(new Pawn(Color.WHITE, new Position(Column.A, Row.FOUR)));
    }

    @Test
    void failIfHurdleExists() {
        Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.TWO));
        Board hurdleBoard = new Board(List.of(
                new Pawn(Color.BLACK, new Position(Column.A, Row.THREE)),
                pawn
        ));

        assertThatThrownBy(() -> pawn.moveTo(new Position(Column.A, Row.FOUR), hurdleBoard))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failIfMoveToBackWhenColorBlack() {
        Pawn pawn = new Pawn(Color.BLACK, new Position(Column.A, Row.SEVEN));
        Board hurdleBoard = new Board(List.of(
                pawn
        ));

        assertThatThrownBy(() -> pawn.moveTo(new Position(Column.A, Row.EIGHT), hurdleBoard))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failIfMoveToBackWhenColorWhite() {
        Pawn pawn = new Pawn(Color.WHITE, new Position(Column.A, Row.TWO));
        Board hurdleBoard = new Board(List.of(
                pawn
        ));

        assertThatThrownBy(() -> pawn.moveTo(new Position(Column.A, Row.ONE), hurdleBoard))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
