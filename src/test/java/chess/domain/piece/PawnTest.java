package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.game.Team;

public class PawnTest extends AbstractTestFixture {

    @DisplayName("백은 처음에 1칸 혹은 2칸 위로 움직일 수 있다.")
    @Test
    void whiteMoveUp_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(Team.WHITE);

        assertThat(pawn.hasMove(createMove(UP, UP))).isTrue();
        assertThat(pawn.hasMove(createMove(UP))).isTrue();
    }

    @DisplayName("흑은 처음에 1칸 혹은 2칸 아래로 움직일 수 있다.")
    @Test
    void blackMoveDown_onceOrTwice_onFirstMove() {
        Piece pawn = new Pawn(Team.BLACK);

        assertThat(pawn.hasMove(createMove(DOWN, DOWN))).isTrue();
        assertThat(pawn.hasMove(createMove(DOWN))).isTrue();
    }

    @DisplayName("백은 아래로 움직일 수 없다")
    @Test
    void whiteCannotMoveDown() {
        Piece pawn = new Pawn(Team.WHITE);

        assertThat(pawn.hasMove(createMove(DOWN, DOWN))).isFalse();
        assertThat(pawn.hasMove(createMove(DOWN))).isFalse();
    }

    @DisplayName("흑은 위로 움직일 수 없다")
    @Test
    void blackCannotMoveUp() {
        Piece pawn = new Pawn(Team.BLACK);

        assertThat(pawn.hasMove(createMove(UP, UP))).isFalse();
        assertThat(pawn.hasMove(createMove(UP))).isFalse();
    }

    @DisplayName("첫 수 이후 1칸 앞으로 움직일 수 있다")
    @Test
    void moveOnceAfterTouch() {
        Piece touchedPawn = new Pawn(Team.WHITE).touch();

        assertThat(touchedPawn.hasMove(createMove(UP))).isTrue();
        assertThat(touchedPawn.hasMove(createMove(UP, UP))).isFalse();
    }

    @DisplayName("백은 윗 대각선으로 공격할 수 있다")
    @Test
    void white_hasAttackMove_UpDiagonal() {
        Piece piece = new Pawn(Team.WHITE);

        assertThat(piece.hasAttackMove(createMove(RIGHT, UP))).isTrue();
        assertThat(piece.hasAttackMove(createMove(LEFT, UP))).isTrue();
    }

    @DisplayName("흑은 아래 대각선으로 공격할 수 있다")
    @Test
    void black_hasAttackMove_DownDiagonal() {
        Piece piece = new Pawn(Team.BLACK);

        assertThat(piece.hasAttackMove(createMove(RIGHT, DOWN))).isTrue();
        assertThat(piece.hasAttackMove(createMove(LEFT, DOWN))).isTrue();
    }
}
