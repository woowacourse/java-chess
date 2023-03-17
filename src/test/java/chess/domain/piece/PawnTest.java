package chess.domain.piece;

import static chess.domain.board.MoveType.ATTACK;
import static chess.domain.board.MoveType.MOVE;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("백은 처음에 1칸 혹은 2칸 위로 움직일 수 있다.")
    @Test
    void whiteMoveUp_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(WHITE);

        assertThat(pawn.isValidMove(new Move(0, 2), MOVE)).isTrue();
        assertThat(pawn.isValidMove(new Move(0, 1), MOVE)).isTrue();
    }

    @DisplayName("흑은 처음에 1칸 혹은 2칸 아래로 움직일 수 있다.")
    @Test
    void blackMoveDown_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(BLACK);

        assertThat(pawn.isValidMove(new Move(0, -2), MOVE)).isTrue();
        assertThat(pawn.isValidMove(new Move(0, -1), MOVE)).isTrue();
    }

    @DisplayName("처음에 3칸 이상 움직일 수 없다")
    @Test
    void canNotMove_moreThan3_onFirstMove() {
        Piece pawn = new Pawn(WHITE);

        assertThat(pawn.isValidMove(new Move(0, 3), MOVE)).isFalse();
    }

    @DisplayName("백은 아래로 움직일 수 없다")
    @Test
    void whiteCannotMoveDown() {
        Piece pawn = new Pawn(WHITE);

        assertThat(pawn.isValidMove(new Move(0, -1), MOVE)).isFalse();
    }

    @DisplayName("흑은 위로 움직일 수 없다")
    @Test
    void blackCannotMoveUp() {
        Piece pawn = new Pawn(BLACK);

        assertThat(pawn.isValidMove(new Move(0, 1), MOVE)).isFalse();
    }

    @DisplayName("첫 수 이후 1칸 앞으로 움직일 수 있다")
    @Test
    void moveOnceAfterTouch() {
        Piece touchedPawn = new Pawn(WHITE).touch();

        assertThat(touchedPawn.isValidMove(new Move(0, 1), MOVE)).isTrue();
    }

    @DisplayName("첫 수 이후 앞으로 2칸 움직일 수 없다")
    @Test
    void canNotMove_moreThan2_AfterTouch() {
        Piece touchedPawn = new Pawn(WHITE).touch();

        assertThat(touchedPawn.isValidMove(new Move(0, 2), MOVE)).isFalse();
    }

    @DisplayName("백은 윗 대각선으로 공격할 수 있다")
    @Test
    void white_hasAttackMove_UpDiagonal() {
        Piece piece = new Pawn(WHITE);

        assertThat(piece.isValidMove(new Move(1, 1), ATTACK)).isTrue();
        assertThat(piece.isValidMove(new Move(-1, 1), ATTACK)).isTrue();
    }

    @DisplayName("흑은 아래 대각선으로 공격할 수 있다")
    @Test
    void black_hasAttackMove_DownDiagonal() {
        Piece piece = new Pawn(BLACK);

        assertThat(piece.isValidMove(new Move(1, -1), ATTACK)).isTrue();
        assertThat(piece.isValidMove(new Move(-1, -1), ATTACK)).isTrue();
    }

    @DisplayName("공격시 수직으로 이동할 수 없다")
    @Test
    void canNotMoveStraight_onAttack() {
        Piece pawn = new Pawn(WHITE);
        Piece touchedPawn = pawn.touch();

        assertThat(pawn.isValidMove(new Move(0, 1), ATTACK)).isFalse();
        assertThat(touchedPawn.isValidMove(new Move(0, 1), ATTACK)).isFalse();
    }

    @DisplayName("이동시 대각선으로 이동할 수 없다")
    @Test
    void canNotMoveDiagonal_onMove() {
        Piece pawn = new Pawn(WHITE);
        Piece touchedPawn = pawn.touch();

        assertThat(pawn.isValidMove(new Move(1, 1), MOVE)).isFalse();
        assertThat(touchedPawn.isValidMove(new Move(1, 1), MOVE)).isFalse();
    }

    @DisplayName("처음으로 touch할때만 새로운 객체가 생성된다")
    @Test
    void touch() {
        Piece pawn = new Pawn(WHITE);
        Piece touchedPawn = pawn.touch();
        Piece touchedPawn2 = touchedPawn.touch();

        assertThat(touchedPawn).isNotSameAs(pawn);
        assertThat(touchedPawn2).isSameAs(touchedPawn);
    }
}
