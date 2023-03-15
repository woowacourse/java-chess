package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Square 클래스")
public class SquareTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("File과 Rank가 주어지면")
        class given_file_and_rank {
            File file = File.A;
            Rank rank = Rank.ONE;

            @Test
            @DisplayName("해당 위치의 Square를 반환한다")
            void it_returns_square() {
                Square square = Square.of(rank, file);
                assertThat(square.getRank()).isEqualTo(1);
                assertThat(square.getFile()).isEqualTo(file);
            }
        }
    }

    @Nested
    @DisplayName("inLine 메서드는")
    class inLine {
        @Nested
        @DisplayName("다른 Square가 주어지면")
        class given_another_square {
            Square from = Square.of(Rank.ONE, File.H);

            @Test
            @DisplayName("현재 Square와 다른 Square가 직선 상에 있다면 true를 반환한다")
            void it_returns_true() {
                Square to1 = Square.of(Rank.ONE, File.B);
                Square to2 = Square.of(Rank.FOUR, File.H);
                assertAll(
                        () -> assertThat(from.inLine(to1)).isTrue(),
                        () -> assertThat(from.inLine(to2)).isTrue()
                );
            }

            @Test
            @DisplayName("현재 Square와 다른 Square가 직선 상에 있지 않다면 false를 반환한다")
            void it_returns_false() {
                Square to = Square.of(Rank.FIVE, File.B);
                assertThat(from.inLine(to)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("inDiagonal 메서드는")
    class inDiagonal {
        @Nested
        @DisplayName("다른 Square가 주어지면")
        class given_another_square {
            Square from = Square.of(Rank.FOUR, File.D);

            @Test
            @DisplayName("현재 Square와 다른 Square가 사선 상에 있다면 true를 반환한다")
            void it_returns_true() {
                Square to1 = Square.of(Rank.ONE, File.A);
                Square to2 = Square.of(Rank.SEVEN, File.G);
                assertAll(
                        () -> assertThat(from.inDiagonal(to1)).isTrue(),
                        () -> assertThat(from.inDiagonal(to2)).isTrue()
                );
            }

            @Test
            @DisplayName("현재 Square와 다른 Square가 사선 상에 있지 않다면 false를 반환한다")
            void it_returns_false() {
                Square to = Square.of(Rank.FIVE, File.B);
                assertThat(from.inDiagonal(to)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("inLShape 메서드는")
    class inLShape {
        @Nested
        @DisplayName("다른 Square가 주어지면")
        class given_another_square {
            Square from = Square.of(Rank.FOUR, File.D);

            @Test
            @DisplayName("현재 Square와 다른 Square가 라지 L자를 그린다면 true를 반환한다")
            void it_returns_true() {
                Square to1 = Square.of(Rank.THREE, File.B);
                Square to2 = Square.of(Rank.SIX, File.E);
                Square to3 = Square.of(Rank.TWO, File.C);
                Square to4 = Square.of(Rank.FIVE, File.F);
                assertAll(
                        () -> assertThat(from.inLShape(to1)).isTrue(),
                        () -> assertThat(from.inLShape(to2)).isTrue(),
                        () -> assertThat(from.inLShape(to3)).isTrue(),
                        () -> assertThat(from.inLShape(to4)).isTrue()
                );
            }

            @Test
            @DisplayName("현재 Square와 다른 Square가 라지 L자를 그리지 않는다면 false를 반환한다")
            void it_returns_false() {
                Square to1 = Square.of(Rank.FOUR, File.B);
                Square to2 = Square.of(Rank.SEVEN, File.D);
                assertAll(
                        () -> assertThat(from.inLShape(to1)).isFalse(),
                        () -> assertThat(from.inLShape(to2)).isFalse()
                );
            }
        }
    }
}
