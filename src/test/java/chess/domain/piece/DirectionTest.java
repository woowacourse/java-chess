package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class DirectionTest {

    static Stream<Arguments> getKnightDirections() {
        return Stream.of(Arguments.of(-2, 1, Direction.LEFT_LEFT_UP),
                Arguments.of(-2, -1, Direction.LEFT_LEFT_DOWN),
                Arguments.of(-1, 2, Direction.LEFT_UP_UP),
                Arguments.of(-1, -2, Direction.LEFT_DOWN_DOWN),
                Arguments.of(2, 1, Direction.RIGHT_RIGHT_UP),
                Arguments.of(2, -1, Direction.RIGHT_RIGHT_DOWN),
                Arguments.of(1, 2, Direction.RIGHT_UP_UP),
                Arguments.of(1, -2, Direction.RIGHT_DOWN_DOWN));
    }

    @DisplayName("fileDiff와 rankDiff가 Knight의 행마 방향과 같다면 해당 행마를 반환한다.")
    @ParameterizedTest
    @MethodSource("getKnightDirections")
    void knightDirections(int fileDiff, int rankDiff, Direction expectedDirection) {
        Direction direction = Direction.findDirection(fileDiff, rankDiff);

        assertThat(direction).isEqualTo(expectedDirection);
    }

    @DisplayName("두 좌표의 x 차이(fileDiff)와 y 차이(rankDiff)를 통해 방향을 알아내는 findDirection 메서드는")
    @Nested
    class Describe_findDirection {

        @DisplayName("fileDiff와 rankDiff가 모두 0이면 예외가 발생한다.")
        @Test
        void cannotFindDirection() {
            assertThatCode(() -> Direction.findDirection(0, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("현재 위치와 도착 위치가 동일합니다.");
        }

        @DisplayName("fileDiff나 rankDiff가 0일 때")
        @Nested
        class Context_EitherFileDiffOrRankDiffZero {

            @DisplayName("fileDiff가 양수라면 Right가 반환된다.")
            @Test
            void right() {
                Direction direction = Direction.findDirection(3, 0);

                assertThat(direction).isEqualTo(Direction.RIGHT);
            }

            @DisplayName("fileDiff가 음수라면 Left가 반환된다.")
            @Test
            void left() {
                Direction direction = Direction.findDirection(-2, 0);

                assertThat(direction).isEqualTo(Direction.LEFT);
            }

            @DisplayName("rankDiff가 양수라면 Up이 반환된다.")
            @Test
            void up() {
                Direction direction = Direction.findDirection(0, 2);

                assertThat(direction).isEqualTo(Direction.UP);
            }

            @DisplayName("rankDiff가 음수라면 Down이 반환된다.")
            @Test
            void down() {
                Direction direction = Direction.findDirection(0, -4);

                assertThat(direction).isEqualTo(Direction.DOWN);
            }
        }

        @DisplayName("fileDiff와 rankDiff의 절대값이 같을 때")
        @Nested
        class Context_BothAbsoluteDiffAreSame {

            @DisplayName("fileDiff와 rankDiff가 모두 양수이면 Right Up이 반환된다.")
            @Test
            void rightUp() {
                Direction direction = Direction.findDirection(2, 2);

                assertThat(direction).isEqualTo(Direction.RIGHT_UP);
            }

            @DisplayName("fileDiff가 양수이고 rankDiff가 음수이면 Right Down이 반환된다.")
            @Test
            void rightDown() {
                Direction direction = Direction.findDirection(2, -2);

                assertThat(direction).isEqualTo(Direction.RIGHT_DOWN);
            }

            @DisplayName("fileDiff와 rankDiff가 모두 음수이면 Left Down이 반환된다.")
            @Test
            void leftDown() {
                Direction direction = Direction.findDirection(-3, -3);

                assertThat(direction).isEqualTo(Direction.LEFT_DOWN);
            }

            @DisplayName("fileDiff가 음수이고 rankDiff가 양수이면 Left Up이 반환된다.")
            @Test
            void leftUp() {
                Direction direction = Direction.findDirection(-3, 3);

                assertThat(direction).isEqualTo(Direction.LEFT_UP);
            }
        }

        @DisplayName("그 외의 경우")
        @Nested
        class Context_OtherCases {

            @DisplayName("예외가 발생한다.")
            @Test
            void cannotFindDirection() {
                assertThatCode(() -> Direction.findDirection(4, 7))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("주어진 위치로의 방향을 찾을 수 없습니다.");
            }
        }
    }
}
