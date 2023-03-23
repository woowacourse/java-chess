package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.Queen;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

public class QueenTest {
    @Nested
    @DisplayName("Queen이 움직인다.")
    class MoveQueen {
        Queen queen = Queen.from(Team.BLACK);

        @Test
        @DisplayName("대각선으로 움직일 수 있다.")
        void Should_Move_When_Diagonal() {
            boolean result = queen.isMovable(A1, H8, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수평으로 움직일 수 있다.")
        void Should_Move_When_Horizontal() {
            boolean result = queen.isMovable(A1, H1, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수직으로 움직일 수 있다.")
        void Should_Move_When_Vertical() {
            boolean result = queen.isMovable(H1, H8, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("좌우상하대각선외에 움직인다면, Queen은 움직일 수 없다.")
        void Should_NotMove_When_Unmovable() {
            boolean result = queen.isMovable(A1, F8, false);
            Assertions.assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("기물을 확인한다.")
    class CheckPiece {
        Queen queen = Queen.from(Team.BLACK);

        @Test
        @DisplayName("Pawn인지 여부를 물어본다.")
        void Should_False_When_IsPawn() {
            Assertions.assertThat(queen.isPawn()).isFalse();
        }

        @Test
        @DisplayName("Knight인지 여부를 물어본다.")
        void Should_False_When_IsKnight() {
            Assertions.assertThat(queen.isKnight()).isFalse();
        }

        @Test
        @DisplayName("King인지 여부를 물어본다.")
        void Should_False_When_IsKing() {
            Assertions.assertThat(queen.isKing()).isFalse();
        }
    }
}
