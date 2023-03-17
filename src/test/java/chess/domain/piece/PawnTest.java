package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("백은 처음에 1칸 혹은 2칸 위로 움직일 수 있다.")
    @Test
    void whiteMoveUp_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(WHITE);

        assertThat(pawn.hasMove(new Move(UP, UP))).isTrue();
        assertThat(pawn.hasMove(new Move(UP))).isTrue();
    }

    @DisplayName("흑은 처음에 1칸 혹은 2칸 아래로 움직일 수 있다.")
    @Test
    void blackMoveDown_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(BLACK);

        assertThat(pawn.hasMove(new Move(DOWN, DOWN))).isTrue();
        assertThat(pawn.hasMove(new Move(DOWN))).isTrue();
    }

    @DisplayName("백은 아래로 움직일 수 없다")
    @Test
    void whiteCannotMoveDown() {
        Piece pawn = new Pawn(WHITE);

        assertThat(pawn.hasMove(new Move(DOWN, DOWN))).isFalse();
        assertThat(pawn.hasMove(new Move(DOWN))).isFalse();
    }

    @DisplayName("흑은 위로 움직일 수 없다")
    @Test
    void blackCannotMoveUp() {
        Piece pawn = new Pawn(BLACK);

        assertThat(pawn.hasMove(new Move(UP, UP))).isFalse();
        assertThat(pawn.hasMove(new Move(UP))).isFalse();
    }

    @DisplayName("첫 수 이후 1칸 앞으로 움직일 수 있다")
    @Test
    void moveOnceAfterTouch() {
        Piece touchedPawn = new Pawn(WHITE).touch();

        assertThat(touchedPawn.hasMove(new Move(UP))).isTrue();
        assertThat(touchedPawn.hasMove(new Move(UP, UP))).isFalse();
    }

    @DisplayName("백은 윗 대각선으로 공격할 수 있다")
    @Test
    void white_hasAttackMove_UpDiagonal() {
        Piece piece = new Pawn(WHITE);

        assertThat(piece.hasAttackMove(new Move(RIGHT, UP))).isTrue();
        assertThat(piece.hasAttackMove(new Move(LEFT, UP))).isTrue();
    }

    @DisplayName("흑은 아래 대각선으로 공격할 수 있다")
    @Test
    void black_hasAttackMove_DownDiagonal() {
        Piece piece = new Pawn(BLACK);

        assertThat(piece.hasAttackMove(new Move(RIGHT, DOWN))).isTrue();
        assertThat(piece.hasAttackMove(new Move(LEFT, DOWN))).isTrue();
    }
}
