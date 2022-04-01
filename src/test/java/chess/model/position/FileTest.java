package chess.model.position;

import static chess.model.position.File.A;
import static chess.model.position.File.B;
import static chess.model.position.File.G;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @DisplayName("1~8까지의 숫자가 아닌 숫자가 들어오면 예외를 발생한다.")
    @ParameterizedTest()
    @ValueSource(ints = {-1, 9, 0, 11})
    void of_exception() {
        assertThatThrownBy(() -> File.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 값이 입력 되었습니다.");
    }

    @DisplayName("File을 이동할수 있으면 true를 반환한다.")
    @Test()
    void canAdd_true() {
        assertThat(G.canAdd(1)).isTrue();
    }

    @DisplayName("File을 이동할수 없으면 false를 반환한다.")
    @Test
    void canAdd_false() {
        assertThat(A.canAdd(-1)).isFalse();
    }

    @DisplayName("File을 이동한다.")
    @Test
    void add() {
        assertThat(A.add(1)).isEqualTo(B);
    }
}
