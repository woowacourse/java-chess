package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.square.Direction;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("화이트 더블 폰 푸시 성공")
    void white_double_pawn_push_success() {
        Pawn pawn = new Pawn(Team.WHITE);
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.FOUR);
        assertThat(pawn.findDirection(current, destination)).isEqualTo(Direction.UP);
    }

    @Test
    @DisplayName("블랙 더블 폰 푸시 성공")
    void black_double_pawn_push_success() {
        Pawn pawn = new Pawn(Team.BLACK);
        Square current = Square.of(File.A, Rank.SEVEN);
        Square destination = Square.of(File.A, Rank.FIVE);
        assertThat(pawn.findDirection(current, destination)).isEqualTo(Direction.DOWN);
    }

    @Test
    @DisplayName("Rank가 2가 아니면 화이트 더블 폰 푸시 실패")
    void white_double_pawn_push_fail() {
        Pawn pawn = new Pawn(Team.WHITE);
        Square current = Square.of(File.A, Rank.THREE);
        Square destination = Square.of(File.A, Rank.FIVE);
        assertThatThrownBy(() -> pawn.findDirection(current, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("Rank가 7이 아니면 블랙 더블 폰 푸시 실패")
    void black_double_pawn_push_fail() {
        Pawn pawn = new Pawn(Team.BLACK);
        Square current = Square.of(File.A, Rank.SIX);
        Square destination = Square.of(File.A, Rank.FOUR);
        assertThatThrownBy(() -> pawn.findDirection(current, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
    }
}
