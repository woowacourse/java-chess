package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.PieceType;
import chessgame.domain.piece.Rook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class RookTest {
    @Nested
    @DisplayName("Rook이 움직인다.")
    class MoveRook {
        Rook rook = Rook.from(Team.BLACK);

        @Test
        @DisplayName("수평으로 움직일 수 있다")
        void Should_Move_When_Horizontal() {
            boolean result = rook.isMovable(A1, F1, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수직으로 움직일 수 있다")
        void Should_Move_When_Vertical() {
            boolean result = rook.isMovable(F1, F8, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("상하좌우외에 움직인다면, Rook은 움직일 수 없다.")
        void Should_NotMove_When_Unmovable() {
            boolean result = rook.isMovable(A1, F8, false);
            Assertions.assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("기물을 확인한다.")
    class CheckPiece {
        Rook rook = Rook.from(Team.BLACK);

        @Test
        @DisplayName("Pawn인지 여부를 물어본다.")
        void Should_False_When_IsPawn() {
            Assertions.assertThat(rook.isPiece(PieceType.PAWN)).isFalse();
        }

        @Test
        @DisplayName("Knight인지 여부를 물어본다.")
        void Should_False_When_IsKnight() {
            Assertions.assertThat(rook.isPiece(PieceType.KNIGHT)).isFalse();
        }

        @Test
        @DisplayName("King인지 여부를 물어본다.")
        void Should_False_When_IsKing() {
            Assertions.assertThat(rook.isPiece(PieceType.KING)).isFalse();
        }
    }
}
