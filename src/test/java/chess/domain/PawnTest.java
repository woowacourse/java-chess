package chess.domain;

import chess.domain.chesspiece.EmptyPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Pawn 클래스")
class PawnTest {

    @Nested
    @DisplayName("from 메서드는")
    class from {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Pawn을 1개 반환한다")
            void it_returns_pawns() {
                Pawn pawn = Pawn.from(Side.BLACK);
                assertThat(pawn).isInstanceOf(Pawn.class);
                assertThat(pawn.isBlack()).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        @Nested
        @DisplayName("초기 위치의 Pawn이 주어지면")
        class given_initialized_pawn {
            Pawn initializedWhitePawn = Pawn.from(Side.WHITE);
            Square initialFrom = Square.of(Rank.TWO, File.A);
            Queen blackQueen = Queen.from(Side.BLACK);
            Queen whiteQueen = Queen.from(Side.WHITE);

            @Test
            @DisplayName("앞의 한 칸에 기물이 없으면 true를 반환한다")
            void it_returns_movable1() {
                Square movableSquare = Square.of(Rank.THREE, File.A);

                assertThat(initializedWhitePawn.isMovable(initialFrom, movableSquare, EmptyPiece.getInstance())).isTrue();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적의 기물이 있으면 true를 반환한다")
            void it_returns_movable2() {
                Square diagonalSquare = Square.of(Rank.THREE, File.B);

                assertThat(initializedWhitePawn.isMovable(initialFrom, diagonalSquare, blackQueen)).isTrue();
            }

            @Test
            @DisplayName("갈 수 없는 위치라면 false를 반환한다")
            void it_returns_not_movable1() {
                Square sameRankSquare = Square.of(Rank.TWO, File.B);
                Square backSquare = Square.of(Rank.ONE, File.A);

                assertThat(initializedWhitePawn.isMovable(initialFrom, sameRankSquare, blackQueen)).isFalse();
                assertThat(initializedWhitePawn.isMovable(initialFrom, backSquare, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("앞의 한 칸 혹은 두 칸에 기물이 있으면 false를 반환한다")
            void it_returns_not_movable2() {
                Square movableSquare1 = Square.of(Rank.FOUR, File.A);
                Square movableSquare2 = Square.of(Rank.THREE, File.A);

                assertThat(initializedWhitePawn.isMovable(initialFrom, movableSquare1, blackQueen)).isFalse();
                assertThat(initializedWhitePawn.isMovable(initialFrom, movableSquare2, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 아니면 false를 반환한다")
            void it_returns_movable3() {
                Square diagonalSquare = Square.of(Rank.THREE, File.B);

                assertThat(initializedWhitePawn.isMovable(initialFrom, diagonalSquare, whiteQueen)).isFalse();
                assertThat(initializedWhitePawn.isMovable(initialFrom, diagonalSquare, EmptyPiece.getInstance())).isFalse();
            }
        }
    }
}
