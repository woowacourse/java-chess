package chess.domain.piece;

import static chess.domain.board.MoveType.ATTACK;
import static chess.domain.board.MoveType.MOVE;
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

        assertThat(pawn.isValidMove(new Move(UP, UP), MOVE)).isTrue();
        assertThat(pawn.isValidMove(new Move(UP), MOVE)).isTrue();
    }

    @DisplayName("흑은 처음에 1칸 혹은 2칸 아래로 움직일 수 있다.")
    @Test
    void blackMoveDown_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(BLACK);

        assertThat(pawn.isValidMove(new Move(DOWN, DOWN), MOVE)).isTrue();
        assertThat(pawn.isValidMove(new Move(DOWN), MOVE)).isTrue();
    }

    @DisplayName("백은 아래로 움직일 수 없다")
    @Test
    void whiteCannotMoveDown() {
        Piece pawn = new Pawn(WHITE);

        assertThat(pawn.isValidMove(new Move(DOWN, DOWN), MOVE)).isFalse();
        assertThat(pawn.isValidMove(new Move(DOWN), MOVE)).isFalse();
    }

    @DisplayName("흑은 위로 움직일 수 없다")
    @Test
    void blackCannotMoveUp() {
        Piece pawn = new Pawn(BLACK);

        assertThat(pawn.isValidMove(new Move(UP, UP), MOVE)).isFalse();
        assertThat(pawn.isValidMove(new Move(UP), MOVE)).isFalse();
    }

    @DisplayName("첫 수 이후 1칸 앞으로 움직일 수 있다")
    @Test
    void moveOnceAfterTouch() {
        Piece touchedPawn = new Pawn(WHITE).touch();

        assertThat(touchedPawn.isValidMove(new Move(UP), MOVE)).isTrue();
        assertThat(touchedPawn.isValidMove(new Move(UP, UP), MOVE)).isFalse();
    }

    @DisplayName("백은 윗 대각선으로 공격할 수 있다")
    @Test
    void white_hasAttackMove_UpDiagonal() {
        Piece piece = new Pawn(WHITE);

        assertThat(piece.isValidMove(new Move(RIGHT, UP), ATTACK)).isTrue();
        assertThat(piece.isValidMove(new Move(LEFT, UP), ATTACK)).isTrue();
    }

    @DisplayName("흑은 아래 대각선으로 공격할 수 있다")
    @Test
    void black_hasAttackMove_DownDiagonal() {
        Piece piece = new Pawn(BLACK);

        assertThat(piece.isValidMove(new Move(RIGHT, DOWN), ATTACK)).isTrue();
        assertThat(piece.isValidMove(new Move(LEFT, DOWN), ATTACK)).isTrue();
    }
}
