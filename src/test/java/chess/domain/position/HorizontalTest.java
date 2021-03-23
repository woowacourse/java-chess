package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HorizontalTest {

    @Test
    @DisplayName("symbol을 통해 객체 생성하는 기능 확인")
    void createUsingSymbol() {
        assertThat(Horizontal.of("a")).isEqualTo(Horizontal.A);
        assertThatThrownBy(() -> Horizontal.of("z"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당하는 가로 위치를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("value를 통해 객체 생성하는 기능 확인")
    void createUsingValue() {
        assertThat(Horizontal.of(1)).isEqualTo(Horizontal.A);
        assertThatThrownBy(() -> Horizontal.of(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당하는 가로 위치를 찾을 수 없습니다.");
    }

}