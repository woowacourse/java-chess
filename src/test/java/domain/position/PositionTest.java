package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(file, rank);

            assertThat(source.isDiagonal(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isDiagonal_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.B, Rank.THREE);

            assertThat(source.isDiagonal(target)).isFalse();
        }

        @Test
        @DisplayName("두 위치가 오른쪽 위 대각선에 존재하면 참을 반환한다.")
        void isRightUp_True() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.C, Rank.THREE);

            assertThat(source.isRightUp(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 오른쪽 위 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isRightUp_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.B, Rank.THREE);

            assertThat(source.isRightUp(target)).isFalse();
        }

        @Test
        @DisplayName("두 위치가 왼쪽 위 대각선에 존재하면 참을 반환한다.")
        void isLeftUp_True() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.THREE);

            assertThat(source.isLeftUp(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 왼쪽 위 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isLeftUp_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.B, Rank.THREE);

            assertThat(source.isLeftUp(target)).isFalse();
        }

        @Test
        @DisplayName("두 위치가 오른쪽 아래 대각선에 존재하면 참을 반환한다.")
        void isRightDown_True() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.C, Rank.ONE);

            assertThat(source.isRightDown(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 오른쪽 아래 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isRightDown_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.B, Rank.THREE);

            assertThat(source.isRightDown(target)).isFalse();
        }

        @Test
        @DisplayName("두 위치가 왼쪽 아래 대각선에 존재하면 참을 반환한다.")
        void isLeftDown_True() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.ONE);

            assertThat(source.isLeftDown(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 왼쪽 아래 대각선에 존재하지 않으면 거짓을 반환한다.")
        void isLeftDown_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.B, Rank.THREE);

            assertThat(source.isLeftDown(target)).isFalse();
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
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(file, rank);

            assertThat(source.isStraight(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치가 직선에 존재하지 않으면 거짓을 반환한다.")
        void isStraight_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.THREE);

            assertThat(source.isStraight(target)).isFalse();
        }

        @Test
        @DisplayName("방향이 위를 가리키면 참을 반환한다.")
        void isUp_True() {
            Position source = PositionGenerator.generate(File.A, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.THREE);

            assertThat(source.isUp(target)).isTrue();
        }

        @Test
        @DisplayName("방향이 위를 가리키지 않으면 거짓을 반환한다.")
        void isUp_False() {
            Position source = PositionGenerator.generate(File.A, Rank.THREE);
            Position target = PositionGenerator.generate(File.A, Rank.TWO);

            assertThat(source.isUp(target)).isFalse();
        }

        @Test
        @DisplayName("방향이 아래를 가리키면 참을 반환한다.")
        void isDown_True() {
            Position source = PositionGenerator.generate(File.A, Rank.THREE);
            Position target = PositionGenerator.generate(File.A, Rank.TWO);

            assertThat(source.isDown(target)).isTrue();
        }

        @Test
        @DisplayName("방향이 아래를 가리키지 않으면 거짓을 반환한다.")
        void isDown_False() {
            Position source = PositionGenerator.generate(File.A, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.THREE);

            assertThat(source.isDown(target)).isFalse();
        }

        @Test
        @DisplayName("방향이 오른쪽을 가리키면 참을 반환한다.")
        void isRight_True() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.C, Rank.TWO);

            assertThat(source.isRight(target)).isTrue();
        }

        @Test
        @DisplayName("방향이 오른쪽을 가리키지 않으면 거짓을 반환한다.")
        void isRight_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.TWO);

            assertThat(source.isRight(target)).isFalse();
        }

        @Test
        @DisplayName("방향이 왼쪽을 가리키면 참을 반환한다.")
        void isLeft_True() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.TWO);

            assertThat(source.isLeft(target)).isTrue();
        }

        @Test
        @DisplayName("방향이 왼쪽을 가리키지 않으면 거짓을 반환한다.")
        void isLeft_False() {
            Position source = PositionGenerator.generate(File.B, Rank.TWO);
            Position target = PositionGenerator.generate(File.C, Rank.TWO);

            assertThat(source.isLeft(target)).isFalse();
        }
    }

    @Nested
    class StepsTest {

        @Test
        @DisplayName("허용하는 랭크 차이라면 참을 반환한다.")
        void isLegalRankStep_True() {
            Position source = PositionGenerator.generate(File.A, Rank.TWO);
            Position target = PositionGenerator.generate(File.A, Rank.SEVEN);

            assertThat(source.isLegalRankStep(target, 5)).isTrue();
        }

        @Test
        @DisplayName("허용하지 않는 랭크 차이라면 거짓을 반환한다.")
        void isLegalRankStep_False() {
            Position source = PositionGenerator.generate(File.A, Rank.ONE);
            Position target = PositionGenerator.generate(File.A, Rank.SEVEN);

            assertThat(source.isLegalRankStep(target, 5)).isFalse();
        }

        @Test
        @DisplayName("허용하는 파일 차이라면 참을 반환한다.")
        void isLegalFileStep_True() {
            Position source = PositionGenerator.generate(File.A, Rank.TWO);
            Position target = PositionGenerator.generate(File.F, Rank.SEVEN);

            assertThat(source.isLegalFileStep(target, 5)).isTrue();
        }

        @Test
        @DisplayName("허용하지 않는 파일 차이라면 거짓을 반환한다.")
        void isLegalFileStep_False() {
            Position source = PositionGenerator.generate(File.A, Rank.TWO);
            Position target = PositionGenerator.generate(File.E, Rank.SEVEN);

            assertThat(source.isLegalFileStep(target, 5)).isFalse();
        }
    }

    @Nested
    class BetweenPositionsTest {

        @Test
        @DisplayName("직선 위 두 위치 사이에 존재하는 위치들을 반환한다.")
        void findBetweenStraightPositions() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.H, Rank.FOUR);

            List<Position> positions = source.findBetweenStraightPositions(target);

            assertThat(positions).containsExactly(
                    PositionGenerator.generate(File.E, Rank.FOUR),
                    PositionGenerator.generate(File.F, Rank.FOUR),
                    PositionGenerator.generate(File.G, Rank.FOUR)
            );
        }

        @Test
        @DisplayName("대각선 위 두 위치 사이에 존재하는 위치들을 반환한다.")
        void findBetweenDiagonalPositions() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.H, Rank.EIGHT);

            List<Position> positions = source.findBetweenDiagonalPositions(target);

            assertThat(positions).containsExactly(
                    PositionGenerator.generate(File.E, Rank.FIVE),
                    PositionGenerator.generate(File.F, Rank.SIX),
                    PositionGenerator.generate(File.G, Rank.SEVEN)
            );
        }
    }
}
