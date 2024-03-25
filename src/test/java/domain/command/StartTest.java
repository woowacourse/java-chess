package domain.command;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartTest {
    @Test
    @DisplayName("시작 상태에서 시작 상태로 넘어가면 예외가 발생한다")
    void next() {
        assertThatThrownBy(() -> Start.getInstance().next(Start.getInstance()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("시작 상태에서 시작 상태로 넘어갈 수 없습니다");
    }
}
