package chess.domain.position;

import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    // up
    @Test
    @DisplayName("한칸 위로 이동한다.")
    void up() {
        assertThat(FIVE.up()).isEqualTo(Rank.SIX);
    }

    @Test
    @DisplayName("rank가 8인 경우 한칸 위로 이동이 불가능하다.")
    void upEightRank() {
        assertThatThrownBy(EIGHT::up)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"1, TWO", "2, THREE", "3, FOUR", "4, FIVE",
            "5, SIX", "6, SEVEN", "7, EIGHT"})
    @DisplayName("현재 rank에서 해당하는 숫자만큼 위로 이동이 가능하다.")
    void up(final int step, final Rank rank) {
        assertThat(ONE.up(step)).isEqualTo(rank);
    }

    @Test
    @DisplayName("현재 rank에서 숫자만큼 위로 이동하여, 8을 초과한 경우 예외가 발생한다.")
    void canNotUpOverEight() {
        assertThatThrownBy(() -> ONE.up(8))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN"})
    @DisplayName("rank가 1 ~ 7인 경우 위로 한칸 이동할 수 있다.")
    void canUp(final Rank rank) {
        assertThat(rank.canUp()).isTrue();
    }

    @Test
    @DisplayName("rank가 8인 경우 위로 한칸 이동할 수 없다.")
    void canUp() {
        assertThat(EIGHT.canUp()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4", "5", "6", "7"})
    @DisplayName("현재 rank에서 해당하는 숫자만큼 위로 이동이 가능한지 여부를 반환힌다.")
    void canUp(final int step) {
        assertAll(
                () -> assertThat(ONE.canUp(step)).isTrue(),
                () -> assertThat(ONE.canUp(8)).isFalse()
        );
    }

    // down
    @Test
    @DisplayName("한칸 아래로 이동한다.")
    void down() {
        assertThat(FIVE.down()).isEqualTo(Rank.FOUR);
    }

    @Test
    @DisplayName("rank가 1인 경우 한칸 아래로 이동이 불가능하다.")
    void downOneRank() {
        assertThatThrownBy(ONE::down)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"1, SEVEN", "2, SIX", "3, FIVE", "4, FOUR",
            "5, THREE", "6, TWO", "7, ONE"})
    @DisplayName("현재 rank에서 해당하는 숫자만큼 아래로 이동이 가능하다.")
    void down(final int step, final Rank rank) {
        assertThat(EIGHT.down(step)).isEqualTo(rank);
    }

    @Test
    @DisplayName("현재 rank에서 숫자만큼 아래로 이동하여, 1을 미만이 된 경우 예외가 발생한다.")
    void canNotDownUnderOne() {
        assertThatThrownBy(() -> EIGHT.down(8))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT"})
    @DisplayName("rank가 2 ~ 8인 경우 아래로 한칸 이동할 수 있다.")
    void canDown(final Rank rank) {
        assertThat(rank.canDown()).isTrue();
    }

    @Test
    @DisplayName("rank가 1인 경우 아래로 한칸 이동할 수 없다.")
    void canDown() {
        assertThat(ONE.canDown()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4", "5", "6", "7"})
    @DisplayName("현재 rank에서 해당하는 숫자만큼 아래로 이동이 가능한지 여부를 반환힌다.")
    void canDown(final int step) {
        assertAll(
                () -> assertThat(EIGHT.canDown(step)).isTrue(),
                () -> assertThat(EIGHT.canDown(8)).isFalse()
        );
    }

    // ---
    @ParameterizedTest
    @CsvSource({"ONE", "THREE"})
    @DisplayName("두 rank간의 거리가 한칸인 경우 참을 반환한다.")
    void canMoveOneSpace(final Rank rank) {
        assertThat(TWO.canMoveOneSpace(rank)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"FOUR", "FIVE", "SIX", "SEVEN", "EIGHT"})
    @DisplayName("두 rank간의 거리가 한칸이 아닌 경우 거짓을 반환한다.")
    void canNotMoveOneSpace(final Rank rank) {
        assertThat(TWO.canMoveOneSpace(rank)).isFalse();
    }
}
