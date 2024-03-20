package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Nested
public class PositionTest {

    @Nested
    class DiagonalPositionTest {

        private static Stream<Arguments> provideFileAndRank() {
            return Stream.of(
                    Arguments.of(File.A, Rank.ONE),
                    Arguments.of(File.A, Rank.THREE),
                    Arguments.of(File.C, Rank.ONE),
                    Arguments.of(File.C, Rank.THREE),
                    Arguments.of(File.D, Rank.FOUR),
                    Arguments.of(File.E, Rank.FIVE),
                    Arguments.of(File.F, Rank.SIX),
                    Arguments.of(File.G, Rank.SEVEN),
                    Arguments.of(File.H, Rank.EIGHT)
            );
        }

        @ParameterizedTest
        @MethodSource("provideFileAndRank")
        @DisplayName("두 위치가 대각선에 존재하면 참을 반환한다.")
        void isDiagonal_True(File file, Rank rank) {
            Position source = new Position(File.B, Rank.TWO);
            Position target = new Position(file, rank);

            assertThat(source.isDiagonal(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isDiagonal_False() {
            Position source = new Position(File.B, Rank.TWO);
            Position target = new Position(File.B, Rank.THREE);

            assertThat(source.isDiagonal(target)).isFalse();
        }
    }

    @Nested
    class StraightPositionTest {

        private static Stream<Arguments> provideFileAndRank() {
            return Stream.of(
                    Arguments.of(File.B, Rank.ONE),
                    Arguments.of(File.B, Rank.THREE),
                    Arguments.of(File.B, Rank.FOUR),
                    Arguments.of(File.B, Rank.FIVE),
                    Arguments.of(File.B, Rank.SIX),
                    Arguments.of(File.B, Rank.SEVEN),
                    Arguments.of(File.B, Rank.EIGHT),
                    Arguments.of(File.A, Rank.TWO),
                    Arguments.of(File.C, Rank.TWO),
                    Arguments.of(File.D, Rank.TWO),
                    Arguments.of(File.E, Rank.TWO),
                    Arguments.of(File.F, Rank.TWO),
                    Arguments.of(File.G, Rank.TWO)
            );
        }

        @ParameterizedTest
        @MethodSource("provideFileAndRank")
        @DisplayName("두 위치가 직선에 존재하면 참을 반환한다.")
        void isStraight_True(File file, Rank rank) {
            Position source = new Position(File.B, Rank.TWO);
            Position target = new Position(file, rank);

            assertThat(source.isStraight(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 직선에 존재하지 않으면 거짓을 반환한다.")
        void isStraight_False() {
            Position source = new Position(File.B, Rank.TWO);
            Position target = new Position(File.A, Rank.THREE);

            assertThat(source.isStraight(target)).isFalse();
        }
    }
}
