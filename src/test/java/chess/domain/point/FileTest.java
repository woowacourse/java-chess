package chess.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @ParameterizedTest
    @ValueSource(chars = {'a', 'h'})
    @DisplayName("a부터 h까지의 가로 위치를 가진다.")
    void createRank(char position) {
        File file = File.from(position);

        assertThat(file).isEqualTo(File.from(position));
    }

    @ParameterizedTest
    @ValueSource(chars = {'z', 'i'})
    @DisplayName("가로 위치가 a 부터 h 사이의 값이 아니라면 예외가 발생한다.")
    void invalidRank(char position) {
        assertThatThrownBy(() -> File.from(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("현재 열에서 원하는 거리만큼 열을 이동시킬 수 있다.")
    void move() {
        File file = File.from('a');

        char movedFile = file.addPosition(5);

        assertThat(movedFile).isEqualTo('f');
    }

    @ParameterizedTest
    @ValueSource(ints = {-4, 3})
    @DisplayName("이동시키려는 열이 a보다 크거나 같고, h보다 작거나 같으면 이동 가능하다.")
    void canMove_true(int distanceToMove) {
        File file = File.from('e');

        boolean result = file.canMove(distanceToMove);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 4})
    @DisplayName("이동시키려는 열이 a보다 작거나, h보다 크다면 이동 불가능하다.")
    void canMove_false(int distanceToMove) {
        File file = File.from('e');

        boolean result = file.canMove(distanceToMove);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"h, 3", "a, -4"})
    @DisplayName("현재 열과 입력된 열의 거리를 계산할 수 있다.")
    void calculateDistanceFrom(char position, int expected) {
        File file = File.from('e');
        File other = File.from(position);

        int actual = file.calculateDistanceFrom(other);

        assertThat(actual).isEqualTo(expected);
    }
}
