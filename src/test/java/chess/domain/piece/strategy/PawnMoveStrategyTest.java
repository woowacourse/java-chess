package chess.domain.piece.strategy;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PawnMoveStrategyTest {

    @Test
    @DisplayName("1칸 전진 성공")
    void move_forward_success() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.UPPER);
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.THREE);
        assertThatCode(() -> pawnMoveStrategy.move(current, destination))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각 전진 성공")
    void move_diagonal_success() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.UPPER);
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.B, Rank.THREE);
        assertThatCode(() -> pawnMoveStrategy.move(current, destination))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("더블폰푸시 전진 성공")
    void move_double_pawn_push_success() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.UPPER);
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.FOUR);
        assertThatCode(() -> pawnMoveStrategy.move(current, destination))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("3칸 전진은 실패한다.")
    void move_forward_three_fail() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.UPPER);
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.FIVE);
        assertThatThrownBy(() -> pawnMoveStrategy.move(current, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("시작점이 아닌 위치에서 더블폰푸시를 할 수 없다.")
    void double_pawn_push_fail_when_not_start_position() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.UPPER);
        Square current = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.A, Rank.FIVE);
        assertThatThrownBy(() -> pawnMoveStrategy.move(current, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("뒤로가기는 불가능하다.")
    void move_backward_fail() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.LOWER);
        Square current = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.A, Rank.FOUR);
        assertThatThrownBy(() -> pawnMoveStrategy.move(current, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 없는 위치입니다.");
    }
}