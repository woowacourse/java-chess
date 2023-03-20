package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("squaresOfPath 메서드는")
    class squaresOfPath {
        @Nested
        @DisplayName("직선 경로가 주어지면")
        class given_linepath {
            Square from = Square.of(Rank.FOUR, File.F);
            Square to1 = Square.of(Rank.FOUR, File.B);
            Square to2 = Square.of(Rank.ONE, File.F);

            @Test
            @DisplayName("사이에 있는 Square들을 반환한다")
            void it_returns_squares() {
                List<Square> squares1 = from.squaresOfPath(to1);
                List<Square> squares2 = from.squaresOfPath(to2);
                assertThat(squares1).containsSequence(Square.of(Rank.FOUR, File.E),
                        Square.of(Rank.FOUR, File.D),
                        Square.of(Rank.FOUR, File.C));
                assertThat(squares2).containsSequence(Square.of(Rank.THREE, File.F),
                        Square.of(Rank.TWO, File.F));
            }
        }

        @Nested
        @DisplayName("사선 경로가 주어지면")
        class given_diagonalpath {
            Square from = Square.of(Rank.FOUR, File.F);
            Square to1 = Square.of(Rank.ONE, File.C);
            Square to2 = Square.of(Rank.EIGHT, File.B);

            @Test
            @DisplayName("사이에 있는 Square들을 반환한다")
            void it_returns_squares() {
                List<Square> squares1 = from.squaresOfPath(to1);
                List<Square> squares2 = from.squaresOfPath(to2);
                assertThat(squares1).containsOnly(Square.of(Rank.THREE, File.E),
                        Square.of(Rank.TWO, File.D));
                assertThat(squares2).containsOnly(Square.of(Rank.FIVE, File.E),
                        Square.of(Rank.SIX, File.D),
                        Square.of(Rank.SEVEN, File.C));
            }
        }

        @Nested
        @DisplayName("직선 경로도 사선 경로도 아니면")
        class given_non_line_non_diagonalpath {
            Square from = Square.of(Rank.FOUR, File.F);
            Square to1 = Square.of(Rank.FIVE, File.A);
            Square to2 = Square.of(Rank.ONE, File.E);
            Square to3 = Square.of(Rank.TWO, File.E);

            @Test
            @DisplayName("아무 Square도 반환하지 않는다")
            void it_returns_empty_square() {
                List<Square> squares1 = from.squaresOfPath(to1);
                List<Square> squares2 = from.squaresOfPath(to2);
                List<Square> squares3 = from.squaresOfPath(to3);
                assertThat(squares1).isEmpty();
                assertThat(squares2).isEmpty();
                assertThat(squares3).isEmpty();
            }
        }
    }
}
