package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Nested
    class StraightDiagonalPositionTest {

        private static Stream<Arguments> provideFileAndRank() {
            return Stream.of(
                    Arguments.of(File.B, Rank.THREE),
                    Arguments.of(File.B, Rank.FIVE),
                    Arguments.of(File.C, Rank.TWO),
                    Arguments.of(File.C, Rank.SIX),
                    Arguments.of(File.E, Rank.TWO),
                    Arguments.of(File.E, Rank.SIX),
                    Arguments.of(File.F, Rank.THREE),
                    Arguments.of(File.F, Rank.FIVE)
            );
        }

        @ParameterizedTest
        @MethodSource("provideFileAndRank")
        @DisplayName("두 위치가 한 칸 직선 한 칸 대각선에 존재하면 참을 반환한다.")
        void isStraightDiagonal_True(File file, Rank rank) {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(file, rank);

            assertThat(source.isStraightDiagonal(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 한 칸 직선 한 칸 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isStraightDiagonal_False() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.A, Rank.THREE);

            assertThat(source.isStraightDiagonal(target)).isFalse();
        }
    }

    @Nested
    class EveryDirectionPositionTest {

        private static Stream<Arguments> provideFileAndRank() {
            return Stream.of(
                    Arguments.of(File.C, Rank.THREE),
                    Arguments.of(File.C, Rank.FOUR),
                    Arguments.of(File.C, Rank.FIVE),
                    Arguments.of(File.D, Rank.THREE),
                    Arguments.of(File.D, Rank.FIVE),
                    Arguments.of(File.E, Rank.THREE),
                    Arguments.of(File.E, Rank.FOUR),
                    Arguments.of(File.E, Rank.FIVE)
            );
        }

        @ParameterizedTest
        @MethodSource("provideFileAndRank")
        @DisplayName("두 위치가 모든 방향 한 칸 내에 존재하면 참을 반환한다.")
        void isNeighbor_True(File file, Rank rank) {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(file, rank);

            assertThat(source.isNeighbor(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 모든 방향 한 칸 내에 존재하지 않으면 거짓을 반환한다.")
        void isNeighbor_False() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.A, Rank.THREE);

            assertThat(source.isNeighbor(target)).isFalse();
        }
    }

    @Nested
    class ForwardPositionTest {

        private static Stream<Arguments> provideFileAndRank() {
            return Stream.of(
                    Arguments.of(File.D, Rank.THREE),
                    Arguments.of(File.D, Rank.FOUR)
            );
        }

        @ParameterizedTest
        @MethodSource("provideFileAndRank")
        @DisplayName("처음에 두 위치가 한 칸 또는 두 칸 앞에 존재하면 참을 반환한다.")
        void isForwardStraight_First_True(File file, Rank rank) {
            Position source = new Position(File.D, Rank.TWO);
            Position target = new Position(file, rank);

            assertThat(source.isForwardStraight(target)).isTrue();
        }

        @Test
        @DisplayName("처음에 두 위치가 한 칸 또는 두 칸 앞에 존재하지 않으면 거짓을 반환한다.")
        void isForwardStraight_First_False() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.EIGHT);

            assertThat(source.isForwardStraight(target)).isFalse();
        }

        @Test
        @DisplayName("처음이 아닐 때 두 위치가 한 칸 앞에 존재하면 참을 반환한다.")
        void isForwardStraight_NotFirst_True() {
            Position source = new Position(File.D, Rank.THREE);
            Position target = new Position(File.D, Rank.FOUR);

            assertThat(source.isForwardStraight(target)).isTrue();
        }

        @Test
        @DisplayName("처음이 아닐 때 두 위치가 한 칸 앞에 존재하지 않으면 거짓을 반환한다.")
        void isForwardStraight_NotFirst_False() {
            Position source = new Position(File.D, Rank.THREE);
            Position target = new Position(File.D, Rank.FIVE);

            assertThat(source.isForwardStraight(target)).isFalse();
        }
    }

    @Nested
    class BetweenPositionsTest {

        @Test
        @DisplayName("직선 위 두 위치 사이에 존재하는 위치들을 반환한다.")
        void findBetweenStraightPositions() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.H, Rank.FOUR);

            List<Position> positions = source.findBetweenStraightPositions(target);

            assertThat(positions).containsExactly(
                    new Position(File.E, Rank.FOUR),
                    new Position(File.F, Rank.FOUR),
                    new Position(File.G, Rank.FOUR)
            );
        }

        @Test
        @DisplayName("대각선 위 두 위치 사이에 존재하는 위치들을 반환한다.")
        void findBetweenDiagonalPositions() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.H, Rank.EIGHT);

            List<Position> positions = source.findBetweenDiagonalPositions(target);

            assertThat(positions).containsExactly(
                    new Position(File.E, Rank.FIVE),
                    new Position(File.F, Rank.SIX),
                    new Position(File.G, Rank.SEVEN)
            );
        }
    }
}
