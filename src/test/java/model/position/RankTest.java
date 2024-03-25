package model.position;

import model.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @DisplayName("적합하지 않은 표현식을 통해 Rank를 찾을 경우 예외가 발생한다.")
    @Test
    void from() {
        assertThatThrownBy(() -> Rank.from('9'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("일치하는 Rank가 존재하지 않습니다.");
    }

    @DisplayName("특정 방향으로의 이동이 불가한 경우 예외가 발생한다.")
    @Test
    void moving() {
        assertThatThrownBy(() -> Rank.EIGHT.moving(Direction.N))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 Rank에서 해당 방향으로 이동할 수 없습니다.");
    }

}
