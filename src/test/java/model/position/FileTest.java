package model.position;

import model.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @DisplayName("적합하지 않은 표현식을 통해 File을 찾을 경우 예외가 발생한다.")
    @Test
    void from() {
        assertThatThrownBy(() -> File.from('i'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("일치하는 File이 존재하지 않습니다.");
    }

    @DisplayName("특정 방향으로의 이동이 불가한 경우 예외가 발생한다.")
    @Test
    void moving() {
        assertThatThrownBy(() -> File.H.moving(Direction.E))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 File에서 해당 방향으로 이동할 수 없습니다.");
    }
}
