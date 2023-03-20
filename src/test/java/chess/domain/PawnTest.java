package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Pawn 클래스")
class PawnTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Pawn을 8개 생성한다")
            void it_returns_pawns() {
                assertThat(Pawn.of(Side.BLACK)).hasSize(8);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        Pawn whitePawn = Pawn.of(Side.WHITE)
                             .get(0);
        Queen blackQueen = Queen.of(Side.BLACK);
        Queen whiteQueen = Queen.of(Side.WHITE);

        @Nested
        @DisplayName("움직인적 없는 Pawn에 대해")
        class context1 {
            Square initialPosition = Square.of(Rank.TWO, File.A);
            Square oneSquareAhead = Square.of(Rank.FOUR, File.A);
            Square twoSquareAhead = Square.of(Rank.THREE, File.A);
            Square oneDiagonalSquareAhead = Square.of(Rank.THREE, File.B);


            @Test
            @DisplayName("한 칸 혹은 두 칸 앞에 기물이 없으면 true를 반환한다")
            void it_returns_true1() {
                assertAll(
                        () -> assertThat(whitePawn.isMovable(initialPosition, oneSquareAhead, EmptyPiece.getInstance())).isTrue(),
                        () -> assertThat(whitePawn.isMovable(initialPosition, twoSquareAhead, EmptyPiece.getInstance())).isTrue()
                );
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 있으면 true를 반환한다")
            void it_returns_true2() {
                assertThat(whitePawn.isMovable(initialPosition, oneDiagonalSquareAhead, blackQueen)).isTrue();
            }

            @Test
            @DisplayName("이동 불가능한 위치라면 false를 반환한다")
            void it_returns_false1() {
                Square sameRankSquare = Square.of(Rank.TWO, File.B);
                Square backSquare = Square.of(Rank.ONE, File.A);

                assertThat(whitePawn.isMovable(initialPosition, sameRankSquare, blackQueen)).isFalse();
                assertThat(whitePawn.isMovable(initialPosition, backSquare, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("한 칸 혹은 두 칸 앞에 기물이 있으면 false를 반환한다")
            void it_returns_false2() {
                assertThat(whitePawn.isMovable(initialPosition, oneSquareAhead, blackQueen)).isFalse();
                assertThat(whitePawn.isMovable(initialPosition, twoSquareAhead, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 아니면 false를 반환한다")
            void it_returns_false3() {
                assertThat(whitePawn.isMovable(initialPosition, oneDiagonalSquareAhead, whiteQueen)).isFalse();
                assertThat(whitePawn.isMovable(initialPosition, oneDiagonalSquareAhead, EmptyPiece.getInstance())).isFalse();
            }
        }

        @Nested
        @DisplayName("이동한 적 있는 Pawn에 대하여")
        class context2 {
            Square fromPosition = Square.of(Rank.THREE, File.B);
            Square oneSquareAhead = Square.of(Rank.FOUR, File.B);
            Square oneLeftDiagonalSquareAhead = Square.of(Rank.FOUR, File.A);
            Square oneRightDiagonalSquareAhead = Square.of(Rank.FOUR, File.C);
            Square twoSquaresAhead = Square.of(Rank.FIVE, File.B);
            Square sameRankSquare = Square.of(Rank.THREE, File.C);
            Square backSquare = Square.of(Rank.TWO, File.B);


            @Test
            @DisplayName("한 칸 앞에 기물이 없으면 true를 반환한다")
            void it_returns_movable1() {
                assertThat(whitePawn.isMovable(fromPosition, oneSquareAhead, EmptyPiece.getInstance())).isTrue();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 있으면 true를 반환한다")
            void it_returns_movable2() {
                assertThat(whitePawn.isMovable(fromPosition, oneLeftDiagonalSquareAhead, blackQueen)).isTrue();
                assertThat(whitePawn.isMovable(fromPosition, oneRightDiagonalSquareAhead, blackQueen)).isTrue();
            }

            @Test
            @DisplayName("이동 불가능한 위치라면 false를 반환한다")
            void it_returns_not_movable1() {
                assertThat(whitePawn.isMovable(fromPosition, twoSquaresAhead, blackQueen)).isFalse();
                assertThat(whitePawn.isMovable(fromPosition, sameRankSquare, blackQueen)).isFalse();
                assertThat(whitePawn.isMovable(fromPosition, backSquare, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("한 칸 앞에 기물이 있으면 false를 반환한다")
            void it_returns_not_movable2() {
                assertThat(whitePawn.isMovable(fromPosition, oneSquareAhead, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 아니면 false를 반환한다")
            void it_returns_not_movable3() {
                assertThat(whitePawn.isMovable(fromPosition, oneLeftDiagonalSquareAhead, whiteQueen)).isFalse();
                assertThat(whitePawn.isMovable(fromPosition, oneRightDiagonalSquareAhead, EmptyPiece.getInstance())).isFalse();
            }
        }
    }
}
