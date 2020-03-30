package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PawnTest {
    Pawn pawn;

    @BeforeEach
    void setUp() {
        pawn = new Pawn(C2, Team.WHITE);
    }

    @Test
    void moveTo() {
        pawn.moveTo(C3);
        assertThat(pawn.getPosition()).isEqualTo(C3);
    }

    @Test
    void canNotMoveTo_Return_True_When_TryStraightAttack() {
        Piece target = new Pawn(C3, Team.BLACK);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pawn.canNotMoveTo(target))
                .withMessage("폰은 전방의 적을 공격할 수 없습니다.");
    }

    @Test
    void canNotMoveTo_Throw_Exception_When_TryDiagonalAttack() {
        Piece target = new Empty(B3);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pawn.canNotMoveTo(target))
                .withMessage("폰은 공격이 아니면 대각선 이동이 불가합니다.");
    }

    @Test
    void canNotMoveTo_Return_True_When_TryJump() {
        Pawn pawn = new Pawn(C4, Team.WHITE);
        Piece target = new Empty(C6);
        assertThat(pawn.canNotMoveTo(target)).isTrue();
    }

    @Test
    void canNotMoveTo_Return_True_When_TryBackMove() {
        Piece target = new Empty(C1);
        assertThat(pawn.canNotMoveTo(target)).isTrue();
    }

    @Test
    void canNotMoveTo_Return_False_When_EmptyThere() {
        Piece target = new Empty(C3);
        assertThat(pawn.canNotMoveTo(target)).isFalse();
    }

    @Test
    void canNotMoveTo_Return_False_When_TryJump() {
        Piece target = new Empty(C4);
        assertThat(pawn.canNotMoveTo(target)).isFalse();
    }

    @Test
    void canNotMoveTo_Return_False_When_TryDiagonalAttack() {
        Piece target = new Pawn(B3, Team.BLACK);
        assertThat(pawn.canNotMoveTo(target)).isFalse();
    }
}