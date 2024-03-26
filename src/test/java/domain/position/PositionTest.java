package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
public class PositionTest {

    @Nested
    class DirectionTest {

        @Test
        @DisplayName("두 위치의 방향이 위를 향하면 참을 반환한다.")
        void isUp_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.D, Rank.EIGHT);

            assertThat(source.isUp(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 아래를 향하면 참을 반환한다.")
        void isDown_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.D, Rank.ONE);

            assertThat(source.isDown(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 오른쪽을 향하면 참을 반환한다.")
        void isRight_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.H, Rank.FOUR);

            assertThat(source.isRight(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 왼쪽을 향하면 참을 반환한다.")
        void isLeft_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.A, Rank.FOUR);

            assertThat(source.isLeft(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 오른쪽위를 향하면 참을 반환한다.")
        void isRightUp_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.H, Rank.EIGHT);

            assertThat(source.isRightUp(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 오른쪽아래를 향하면 참을 반환한다.")
        void isRightDown_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.G, Rank.ONE);

            assertThat(source.isRightDown(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 왼쪽위를 향하면 참을 반환한다.")
        void isLeftUp_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.A, Rank.SEVEN);

            assertThat(source.isLeftUp(target)).isTrue();
        }

        @Test
        @DisplayName("두 위치의 방향이 왼쪽아래를 향하면 참을 반환한다.")
        void isLeftDown_True() {
            Position source = PositionGenerator.generate(File.D, Rank.FOUR);
            Position target = PositionGenerator.generate(File.A, Rank.ONE);

            assertThat(source.isLeftDown(target)).isTrue();
        }
    }

    @Nested
    class StepTest {

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

            List<Position> positions = source.betweenPositions(target);

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

            List<Position> positions = source.betweenPositions(target);

            assertThat(positions).containsExactly(
                    PositionGenerator.generate(File.E, Rank.FIVE),
                    PositionGenerator.generate(File.F, Rank.SIX),
                    PositionGenerator.generate(File.G, Rank.SEVEN)
            );
        }
    }
}
