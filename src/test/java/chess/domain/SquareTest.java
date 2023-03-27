package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                assertThat(square.getRankSymbol()).isEqualTo(rank.getSymbol());
                assertThat(square.getFileSymbol()).isEqualTo(file.getSymbol());
            }
        }
    }

    @Nested
    @DisplayName("hasSameRank 메서드는")
    class hasSameRank {
        Square from = Square.of(Rank.FOUR, File.E);

        @Nested
        @DisplayName("같은 랭크를 가지는 Square가 주어지면")
        class given_same_rank_square {
            Square to = Square.of(Rank.FOUR, File.D);

            @Test
            @DisplayName("true를 반환한다")
            void it_returns_true() {
                assertThat(from.hasSameRank(to)).isTrue();
            }
        }

        @Nested
        @DisplayName("다른 랭크를 가지는 Square가 주어지면")
        class given_different_rank_square {
            Square to = Square.of(Rank.THREE, File.D);

            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(from.hasSameRank(to)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("hasSameFile 메서드는")
    class hasSameFile {
        Square from = Square.of(Rank.FOUR, File.E);

        @Nested
        @DisplayName("같은 파일을 가지는 Square가 주어지면")
        class given_same_file_square {
            Square to = Square.of(Rank.ONE, File.E);

            @Test
            @DisplayName("true를 반환한다")
            void it_returns_true() {
                assertThat(from.hasSameFile(to)).isTrue();
            }
        }

        @Nested
        @DisplayName("다른 파일을 가지는 Square가 주어지면")
        class given_different_file_square {
            Square to = Square.of(Rank.THREE, File.D);

            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(from.hasSameRank(to)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("isStraight 메서드는")
    class isStraight {
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
                        () -> assertThat(from.isStraight(to1)).isTrue(),
                        () -> assertThat(from.isStraight(to2)).isTrue()
                );
            }

            @Test
            @DisplayName("현재 Square와 다른 Square가 직선 상에 있지 않다면 false를 반환한다")
            void it_returns_false() {
                Square to = Square.of(Rank.FIVE, File.B);
                assertThat(from.isStraight(to)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("isDiagonal 메서드는")
    class isDiagonal {
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
                        () -> assertThat(from.isDiagonal(to1)).isTrue(),
                        () -> assertThat(from.isDiagonal(to2)).isTrue()
                );
            }

            @Test
            @DisplayName("현재 Square와 다른 Square가 사선 상에 있지 않다면 false를 반환한다")
            void it_returns_false() {
                Square to = Square.of(Rank.FIVE, File.B);
                assertThat(from.isDiagonal(to)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("calculatePath 메서드는")
    class calculatePath {
        @Nested
        @DisplayName("직선 경로가 주어지면")
        class given_straight_path {
            Square from = Square.of(Rank.FOUR, File.F);
            Square to1 = Square.of(Rank.FOUR, File.B);
            Square to2 = Square.of(Rank.ONE, File.F);

            @Test
            @DisplayName("사이에 있는 Square들을 반환한다")
            void it_returns_squares() {
                List<Square> squares1 = from.calculatePath(to1);
                List<Square> squares2 = from.calculatePath(to2);
                assertThat(squares1).containsSequence(Square.of(Rank.FOUR, File.E),
                        Square.of(Rank.FOUR, File.D),
                        Square.of(Rank.FOUR, File.C));
                assertThat(squares2).containsSequence(Square.of(Rank.THREE, File.F),
                        Square.of(Rank.TWO, File.F));
            }
        }

        @Nested
        @DisplayName("사선 경로가 주어지면")
        class given_diagonal_path {
            Square from = Square.of(Rank.FOUR, File.F);
            Square to1 = Square.of(Rank.ONE, File.C);
            Square to2 = Square.of(Rank.EIGHT, File.B);

            @Test
            @DisplayName("사이에 있는 Square들을 반환한다")
            void it_returns_squares() {
                List<Square> squares1 = from.calculatePath(to1);
                List<Square> squares2 = from.calculatePath(to2);
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
                List<Square> squares1 = from.calculatePath(to1);
                List<Square> squares2 = from.calculatePath(to2);
                List<Square> squares3 = from.calculatePath(to3);
                assertThat(squares1).isEmpty();
                assertThat(squares2).isEmpty();
                assertThat(squares3).isEmpty();
            }
        }
    }
}
