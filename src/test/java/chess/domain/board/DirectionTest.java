package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DirectionTest {

    @Test
    @DisplayName("방향을 찾을수 없는 경우를 테스트한다.")
    void findNoDirection() {
        assertThatThrownBy(() -> {
            Direction.find(1, 5);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("방향을 찾을수 있는 경우를 테스트한다.")
    void findDirection() {
        assertThat(Direction.find(1, 2)).isEqualTo(Direction.EEN);
    }
}