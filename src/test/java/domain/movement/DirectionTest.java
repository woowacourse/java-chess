package domain.movement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {
    @Test
    void 움직임이_없으면_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> Direction.of(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 위치입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "4, 0"})
    void 위_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.UP);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1, 0", "-4, 0"})
    void 아래_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.DOWN);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 1", "0, 4"})
    void 오른쪽_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.RIGHT);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, -1", "0, -4"})
    void 왼쪽_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.LEFT);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 1", "4, 4"})
    void 오른쪽_위_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.UP_RIGHT);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, -1", "4, -4"})
    void 왼쪽_위_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.UP_LEFT);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1, 1", "-4, 4"})
    void 오른쪽_아래_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.DOWN_RIGHT);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1, -1", "-4, -4"})
    void 왼쪽_아래_방향을_반환한다(int rankDiff, int fileDiff) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(Direction.DOWN_LEFT);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 1, KNIGHT_UP_RIGHT", "1, 2, KNIGHT_RIGHT_UP",
            "-1, 2, KNIGHT_RIGHT_DOWN", "-2, 1, KNIGHT_DOWN_RIGHT",
            "-2, -1, KNIGHT_DOWN_LEFT", "-1, -2, KNIGHT_LEFT_DOWN",
            "1, -2, KNIGHT_LEFT_UP", "2, -1, KNIGHT_UP_LEFT"})
    void 나이트_방향을_반환한다(int rankDiff, int fileDiff, Direction expected) {
        Direction direction = Direction.of(rankDiff, fileDiff);

        Assertions.assertThat(direction).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"4, 5", "2, 4"})
    void 올바르지_않은_방향이면_예외가_발생한다(int rankDiff, int fileDiff) {
        Assertions.assertThatThrownBy(() -> Direction.of(rankDiff, fileDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 방향입니다.");
    }
}
