package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    public static final Position B5 = new Position(File.B, Rank.FIVE);
    public static final Position A4 = new Position(File.A, Rank.FOUR);
    public static final Position B6 = new Position(File.B, Rank.SIX);
    public static final Position B2 = new Position(File.B, Rank.TWO);
    public static final Position B4 = new Position(File.B, Rank.FOUR);
    public static final Position B7 = new Position(File.B, Rank.SEVEN);
    public static final Position B3 = new Position(File.B, Rank.THREE);
    public static final Position E4 = new Position(File.E, Rank.FOUR);
    public static final Position C4 = new Position(File.C, Rank.FOUR);
    public static final Position C5 = new Position(File.C, Rank.FIVE);
    public static final Position C6 = new Position(File.C, Rank.SIX);
    public static final Position D5 = new Position(File.D, Rank.FIVE);

    @Test
    void computePath_Black() {
        final var pawn = new Pawn(Color.BLACK);

        assertThat(pawn.computePath(B5, A4)).containsExactly(A4);
    }

    @Test
    void computePath_White() {
        final var pawn = new Pawn(Color.WHITE);

        assertThat(pawn.computePath(B5, B6)).containsExactly(B6);
    }

    @Test
    void computePath_WhiteTwo() {
        final var pawn = new Pawn(Color.WHITE);

        assertThat(pawn.computePath(B2, B4)).containsExactlyInAnyOrder(B3, B4);
    }

    @Test
    void computePath_WhiteTwoException() {
        final var pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.computePath(B5, B7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void computePath_BlackTwo() {
        final var pawn = new Pawn(Color.BLACK);

        assertThat(pawn.computePath(B7, B5)).containsExactlyInAnyOrder(B6, B5);
    }

    @Test
    void computePath_BlackTwoException() {
        final var pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.computePath(B5, B3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void computePath_BlackIllegalTarget_Exception() {
        final var pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.computePath(B5, E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void computePath_WhiteIllegalTarget_Exception() {
        final var pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.computePath(B5, E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canMove() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMove(Map.of(target, false), source, target)).isTrue();
    }

    @Test
    void canMove3() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = C5;

        assertThat(pawn.canMove(Map.of(target, true), source, target)).isTrue();
    }

    @Test
    void canMove2() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMove(Map.of(target, true), source, target)).isFalse();
    }

    @Test
    void canMove4() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMove(Map.of(target, false), source, target)).isTrue();
    }

    @Test
    void canMove5() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = C6;

        assertThat(pawn.canMove(Map.of(C5, true, C6, false), source, target)).isFalse();
    }
}
