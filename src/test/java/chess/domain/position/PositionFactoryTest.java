package chess.domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionFactoryTest {
    
    @Test
    @DisplayName("PositionFactory에서 Position을 가져온다.")
    void createPosition() {
        Position position = PositionFactory.from("a1");
        Assertions.assertThat(position).isEqualTo(Position.from("a1"));
    }
    
    @Test
    @DisplayName("PositionFactory에서 Position을 가져올 때, 보드에 존재하지 않는 좌표를 입력하면 예외를 던진다.")
    void createPositionException() {
        Assertions.assertThatThrownBy(() -> PositionFactory.from("a9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PositionFactory.POSITION_NOT_IN_BOARD_ERROR_MESSAGE);
    }
}