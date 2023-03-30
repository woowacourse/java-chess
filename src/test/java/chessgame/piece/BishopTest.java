package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.Bishop;
import chessgame.domain.piece.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class BishopTest {
    @Nested
    @DisplayName("Bishop이 움직인다.")
    class MoveBishop {
        Bishop bishop = Bishop.from(Team.BLACK);

        @Test
        @DisplayName("대각선으로 움직일 수 있다.")
        void Should_Move_When_RightDiagonal() {
            boolean result = bishop.isMovable(A1, H8, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("반대 대각선으로 움직일 수 있다.")
        void Should_Move_When_LeftDiagonal() {
            boolean result = bishop.isMovable(H1, A8, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("대각선으로 이동하지 않는다면, 움직일 수 없다.")
        void Should_NotMove_When_Unmovable() {
            boolean result = bishop.isMovable(A1, H1, false);
            Assertions.assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("기물을 확인한다.")
    class CheckPiece {
        Bishop bishop = Bishop.from(Team.BLACK);

        @Test
        @DisplayName("Pawn인지 여부를 물어본다.")
        void Should_False_When_IsPawn() {
            Assertions.assertThat(bishop.isPiece(PieceType.PAWN)).isFalse();
        }

        @Test
        @DisplayName("Knight인지 여부를 물어본다.")
        void Should_False_When_IsKnight() {
            Assertions.assertThat(bishop.isPiece(PieceType.KNIGHT)).isFalse();
        }

        @Test
        @DisplayName("King인지 여부를 물어본다.")
        void Should_False_When_IsKing() {
            Assertions.assertThat(bishop.isPiece(PieceType.KING)).isFalse();
        }
    }
}
