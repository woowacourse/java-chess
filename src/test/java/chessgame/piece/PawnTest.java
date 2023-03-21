package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class PawnTest {
    @Test
    @DisplayName("흑팀 Pawn은 시작 시 아래로 2칸 움직일 수 있다.")
    void Should_MoveVerticalTwoDistance_When_BlackPawnStart() {
        Pawn pawn = Pawn.from(Team.BLACK);
        boolean result = pawn.isMovable(F7, F5);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("백팀 Pawn은 시작 시 위로 2칸 움직일 수 있다.")
    void Should_MoveVerticalTwoDistance_When_WhitePawnStart() {
        Pawn pawn = Pawn.from(Team.WHITE);
        boolean result = pawn.isMovable(A2, A4);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("흑팀 Pawn은 아래로 1칸 움직일 수 있다.")
    void Should_MoveVerticalOneDistance_When_BlackPawn() {
        Pawn pawn = Pawn.from(Team.BLACK);
        boolean result = pawn.isMovable(A4, A3);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("백팀 Pawn은 위로 1칸 움직일 수 있다.")
    void Should_MoveVerticalOneDistance_When_WhitePawn() {
        Pawn pawn = Pawn.from(Team.WHITE);
        boolean result = pawn.isMovable(A3, A4);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("흑팀 Pawn은 공격시 대각선 밑으로 1칸 움직일 수 있다.")
    void Should_MoveDiagonalOneDistance_When_BlackPawnAttack() {
        Pawn pawn = Pawn.from(Team.BLACK);
        boolean result = pawn.isAttack(A4, B3);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("백팀 Pawn은 공격 시 대각선 위로 1칸 움직일 수 있다.")
    void Should_MoveDiagonalOneDistance_When_WhitePawnAttack() {
        Pawn pawn = Pawn.from(Team.WHITE);
        boolean result = pawn.isAttack(A2, B3);
        Assertions.assertThat(result).isTrue();
    }
}
