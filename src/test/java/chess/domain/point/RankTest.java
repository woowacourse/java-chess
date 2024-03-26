package chess.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    @DisplayName("1부터 8까지의 세로 위치를 가진다.")
    void createRank(int position) {
        Rank rank = Rank.from(position);

        assertThat(rank).isEqualTo(Rank.from(position));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("세로 위치가 1보다 작거나 8보다 크다면 예외가 발생한다.")
    void invalidRank(int position) {
        assertThatThrownBy(() -> Rank.from(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("현재 행에서 원하는 거리만큼 행을 이동시킬 수 있다.")
    void move() {
        Rank rank = Rank.from(1);

        int movedRank = rank.addPosition(5);

        assertThat(movedRank).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(ints = {-4, 3})
    @DisplayName("이동시키려는 행이 1보다 크거나 같고, 8보다 작거나 같으면 이동 가능하다.")
    void canMove_true(int distanceToMove) {
        Rank rank = Rank.from(5);

        boolean result = rank.canMove(distanceToMove);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 4})
    @DisplayName("이동시키려는 행이 1보다 작거나, 8보다 크다면 이동 불가능하다.")
    void canMove_false(int distanceToMove) {
        Rank rank = Rank.from(5);

        boolean result = rank.canMove(distanceToMove);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"8, 3", "1, -4"})
    @DisplayName("현재 행과 입력된 행의 거리를 계산할 수 있다.")
    void calculateDistanceFrom(int position, int expected) {
        Rank rank = Rank.from(5);
        Rank other = Rank.from(position);

        int actual = rank.calculateDistanceFrom(other);

        assertThat(actual).isEqualTo(expected);
    }
}
