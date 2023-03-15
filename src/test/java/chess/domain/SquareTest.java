package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
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

    @Nested
    @DisplayName("inPawnsCatchableRange 메서드는")
    class inPawnsCatchableRange {
        @Nested
        @DisplayName("다른 Square가 주어졌을 때")
        class given_another_square {
            Square from = Square.of(Rank.FOUR, File.D);

            @Test
            @DisplayName("Pawn이 잡을 수 있는 범위라면 true를 반환한다")
            void it_returns_true() {
                Square whiteTo1 = Square.of(Rank.FIVE, File.C);
                Square whiteTo2 = Square.of(Rank.FIVE, File.E);
                Square blackTo1 = Square.of(Rank.THREE, File.C);
                Square blackTo2 = Square.of(Rank.THREE, File.E);
                assertAll(
                        () -> assertThat(from.inPawnsCatchableRange(whiteTo1, Side.WHITE)).isTrue(),
                        () -> assertThat(from.inPawnsCatchableRange(whiteTo2, Side.WHITE)).isTrue(),
                        () -> assertThat(from.inPawnsCatchableRange(blackTo1, Side.BLACK)).isTrue(),
                        () -> assertThat(from.inPawnsCatchableRange(blackTo2, Side.BLACK)).isTrue()
                );
            }

            @Test
            @DisplayName("Pawn이 잡을 수 없는 범위라면 false를 반환한다")
            void it_returns_false() {
                Square whiteTo1 = Square.of(Rank.FIVE, File.D);
                Square whiteTo2 = Square.of(Rank.SIX, File.D);
                Square blackTo1 = Square.of(Rank.THREE, File.D);
                Square blackTo2 = Square.of(Rank.TWO, File.D);
                assertAll(
                        () -> assertThat(from.inPawnsCatchableRange(whiteTo1, Side.WHITE)).isFalse(),
                        () -> assertThat(from.inPawnsCatchableRange(whiteTo2, Side.WHITE)).isFalse(),
                        () -> assertThat(from.inPawnsCatchableRange(blackTo1, Side.BLACK)).isFalse(),
                        () -> assertThat(from.inPawnsCatchableRange(blackTo2, Side.BLACK)).isFalse()
                );
            }
        }
    }

    @Nested
    @DisplayName("inPawnsInitialMovableRange 메서드는")
    class inPawnsInitialMovableRange {
        @Nested
        @DisplayName("다른 Square가 주어졌을 때")
        class given_another_square {
            Square from = Square.of(Rank.FOUR, File.D);

            @Test
            @DisplayName("Pawn이 초기 위치에서 움직일 수 있는 범위라면 true를 반환한다")
            void it_returns_true() {
                Square whiteTo1 = Square.of(Rank.FIVE, File.D);
                Square whiteTo2 = Square.of(Rank.SIX, File.D);
                Square blackTo1 = Square.of(Rank.THREE, File.D);
                Square blackTo2 = Square.of(Rank.TWO, File.D);

                assertAll(
                        () -> assertThat(from.inPawnsInitialMovableRange(whiteTo1, Side.WHITE)).isTrue(),
                        () -> assertThat(from.inPawnsInitialMovableRange(whiteTo2, Side.WHITE)).isTrue(),
                        () -> assertThat(from.inPawnsInitialMovableRange(blackTo1, Side.BLACK)).isTrue(),
                        () -> assertThat(from.inPawnsInitialMovableRange(blackTo2, Side.BLACK)).isTrue()
                );
            }

            @Test
            @DisplayName("Pawn이 초기 위치에서 움직일 수 없는 범위라면 false를 반환한다")
            void it_returns_false() {
                Square whiteTo1 = Square.of(Rank.FOUR, File.B);
                Square whiteTo2 = Square.of(Rank.FIVE, File.C);
                Square whiteTo3 = Square.of(Rank.THREE, File.D);
                Square blackTo1 = Square.of(Rank.FOUR, File.F);
                Square blackTo2 = Square.of(Rank.THREE, File.E);
                Square blackTo3 = Square.of(Rank.FIVE, File.D);
                assertSoftly(
                        d -> {
                            assertThat(from.inPawnsInitialMovableRange(whiteTo1, Side.WHITE)).isFalse();
                            assertThat(from.inPawnsInitialMovableRange(whiteTo2, Side.WHITE)).isFalse();
                            assertThat(from.inPawnsInitialMovableRange(whiteTo3, Side.WHITE)).isFalse();
                            assertThat(from.inPawnsInitialMovableRange(blackTo1, Side.BLACK)).isFalse();
                            assertThat(from.inPawnsInitialMovableRange(blackTo2, Side.BLACK)).isFalse();
                            assertThat(from.inPawnsInitialMovableRange(blackTo3, Side.BLACK)).isFalse();
                        }
                );
            }
        }
    }

    @Nested
    @DisplayName("inPawnsMovableRange 메서드는")
    class inPawnsMovableRange {
        @Nested
        @DisplayName("다른 Square가 주어졌을 때")
        class given_another_square {
            Square from = Square.of(Rank.FOUR, File.D);

            @Test
            @DisplayName("Pawn이 움직일 수 있는 범위라면 true를 반환한다")
            void it_returns_true() {
                Square whiteTo = Square.of(Rank.FIVE, File.D);
                Square blackTo = Square.of(Rank.THREE, File.D);
                assertAll(
                        () -> assertThat(from.inPawnsMovableRange(whiteTo, Side.WHITE)).isTrue(),
                        () -> assertThat(from.inPawnsMovableRange(blackTo, Side.BLACK)).isTrue()
                );
            }

            @Test
            @DisplayName("Pawn이 움직일 수 없는 범위라면 false를 반환한다")
            void it_returns_false() {
                Square whiteTo1 = Square.of(Rank.FOUR, File.B);
                Square whiteTo2 = Square.of(Rank.FIVE, File.C);
                Square whiteTo3 = Square.of(Rank.THREE, File.D);
                Square blackTo1 = Square.of(Rank.FOUR, File.E);
                Square blackTo2 = Square.of(Rank.THREE, File.E);
                Square blackTo3 = Square.of(Rank.FIVE, File.D);
                assertAll(
                        () -> assertThat(from.inPawnsMovableRange(whiteTo1, Side.WHITE)).isFalse(),
                        () -> assertThat(from.inPawnsMovableRange(whiteTo2, Side.WHITE)).isFalse(),
                        () -> assertThat(from.inPawnsMovableRange(whiteTo3, Side.WHITE)).isFalse(),
                        () -> assertThat(from.inPawnsMovableRange(blackTo1, Side.BLACK)).isFalse(),
                        () -> assertThat(from.inPawnsMovableRange(blackTo2, Side.BLACK)).isFalse(),
                        () -> assertThat(from.inPawnsMovableRange(blackTo3, Side.BLACK)).isFalse()
                );
            }
        }
    }
}
