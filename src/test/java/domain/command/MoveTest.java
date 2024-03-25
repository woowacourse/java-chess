package domain.command;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveTest {

    @Test
    @DisplayName("움직임 상태에서 시작 상태로 넘어가면 예외가 발생한다")
    void moveToStart() {
        assertThatThrownBy(() -> Move.getInstance().next(Start.getInstance()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("움직임 상태에서 시작 상태로 넘어갈 수 없습니다");
    }
}
