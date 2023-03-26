package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.King;
import chessgame.domain.piece.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

public class KingTest {
    @Nested
    @DisplayName("King이 움직인다.")
    class MoveKing {
        King king = King.from(Team.BLACK);

        @Test
        @DisplayName("수평으로 1칸 움직일 수 있다")
        void Should_Move_When_HorizontalOneDistance() {
            boolean result = king.isMovable(A1, A2, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수직으로 1칸 움직일 수 있다")
        void Should_Move_When_VerticalOneDistance() {
            boolean result = king.isMovable(A1, B1, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("대각선으로 1칸 움직일 수 있다")
        void Should_Move_When_DiagonalOneDistance() {
            boolean result = king.isMovable(A2, B1, false);
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("수평으로 1칸 초과로는 움직일 수 없다")
        void Should_Move_When_HorizontalTwoDistance() {
            boolean result = king.isMovable(A1, A3, false);
            Assertions.assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("기물을 확인한다.")
    class CheckPiece {
        King king = King.from(Team.BLACK);

        @Test
        @DisplayName("Pawn인지 여부를 물어본다.")
        void Should_False_When_IsPawn() {
            Assertions.assertThat(king.isPiece(PieceType.PAWN)).isFalse();
        }

        @Test
        @DisplayName("Knight인지 여부를 물어본다.")
        void Should_False_When_IsKnight() {
            Assertions.assertThat(king.isPiece(PieceType.KNIGHT)).isFalse();
        }

        @Test
        @DisplayName("King인지 여부를 물어본다.")
        void Should_False_When_IsKing() {
            Assertions.assertThat(king.isPiece(PieceType.KING)).isTrue();
        }
    }
}
