package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("폰은 앞에 적이 없을 때, 앞으로 한 칸 이동할 수 있다.")
    @Test
    void Pawn_canMoveUp() {
        // given
        Pawn pawn = new Pawn(TeamColor.BLACK);
        Piece targetPiece = new EmptyPiece();

        // when
        boolean canMove = pawn.canMove(targetPiece, A1, A2);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰은 앞에 적이 있으면, 앞으로 한 칸 이동할 수 없다.")
    @Test
    void Pawn_cannotMoveUp_whenExistEnemy() {
        // given
        Pawn pawn = new Pawn(TeamColor.BLACK);
        Piece targetPiece = new Pawn(TeamColor.WHITE);

        // when
        boolean canMove = pawn.canMove(targetPiece, A1, A2);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("폰은 대각선에 적이 있으면 대각선 한칸 이동할 수 있다.")
    @Test
    void Pawn_canMoveDiagonal_whenExistEnemy() {
        // given
        Pawn pawn = new Pawn(TeamColor.BLACK);
        Piece targetPiece = new Pawn(TeamColor.WHITE);

        // when
        boolean canMove = pawn.canMove(targetPiece, B1, C2);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰은 대각선에 적이 있으면 대각선 한칸 이동할 수 있다.")
    @Test
    void Pawn_canMoveDiagonal_whenExistEnemy2() {
        // given
        Pawn pawn = new Pawn(TeamColor.BLACK);
        Piece targetPiece = new Pawn(TeamColor.WHITE);

        // when
        boolean canMove = pawn.canMove(targetPiece, B1, A2);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰은 대각선에 적이 없으면 대각선으로는 이동할 수 없다.")
    @Test
    void Pawn_cannotMoveDiagonal_whenEmptyTarget() {
        // given
        Pawn pawn = new Pawn(TeamColor.BLACK);
        Piece targetPiece = new EmptyPiece();

        // when
        boolean canMove = pawn.canMove(targetPiece, B1, A2);

        // then
        assertThat(canMove).isFalse();
    }

}