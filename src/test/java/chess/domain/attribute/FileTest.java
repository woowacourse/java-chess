package chess.domain.attribute;

import static chess.domain.attribute.File.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {
    @DisplayName("1~8 범위를 벗어나는 열 번호를 입력하면 예외가 발생한다.")
    @ValueSource(ints = {0, 9})
    @ParameterizedTest
    void constructor(int column) {
        assertThatThrownBy(() -> of(column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일은 1~8 사이로 입력해주세요: " + column);
    }

    @DisplayName("왼쪽 칸을 반환한다.")
    @Test
    void left() {
        assertThat(D.left()).isEqualTo(C);
    }

    @DisplayName("오른쪽 칸을 반환한다.")
    @Test
    void right() {
        assertThat(D.right()).isEqualTo(E);
    }

    @DisplayName("왼쪽으로 이동하지 못하면 예외를 발생시킨다.")
    @Test
    void leftException() {
        assertThatThrownBy(A::left)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("오른쪽으로 이동하지 못하면 예외를 발생시킨다.")
    @Test
    void rightException() {
        assertThatThrownBy(H::right)
                .isInstanceOf(IllegalStateException.class);
    }
}
