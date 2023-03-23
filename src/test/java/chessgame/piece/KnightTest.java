package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.Knight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class KnightTest {
    @Nested
    @DisplayName("Knight가 움직인다.")
    class MoveKnight {
        Knight knight = Knight.from(Team.BLACK);

        @Test
        @DisplayName("수직으로 한칸 이동 후 진행방향으로 대각선을 한칸 이동할 수 있다.")
        void Should_Move_When_VerticalOneDistance() {
            boolean result = knight.isMovable(A1, B3, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수평으로 한칸 이동 후 진행방향으로 대각선을 한칸 이동할 수 있다.")
        void Should_Move_When_HorizontalOneDistance() {
            boolean result = knight.isMovable(H8, F7, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수평으로 한칸 이동 후 역진행방향으로 대각선을 한칸 이동할 수 없다.")
        void Should_NotMove_When_HorizontalOneDistanceReverse() {
            boolean result = knight.isMovable(F8, F7, false);
            Assertions.assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("기물을 확인한다.")
    class CheckPiece {
        Knight knight = Knight.from(Team.BLACK);

        @Test
        @DisplayName("Pawn인지 여부를 물어본다.")
        void Should_False_When_IsPawn() {
            Assertions.assertThat(knight.isPawn()).isFalse();
        }

        @Test
        @DisplayName("Knight인지 여부를 물어본다.")
        void Should_False_When_IsKnight() {
            Assertions.assertThat(knight.isKnight()).isTrue();
        }

        @Test
        @DisplayName("King인지 여부를 물어본다.")
        void Should_False_When_IsKing() {
            Assertions.assertThat(knight.isKing()).isFalse();
        }
    }
}
