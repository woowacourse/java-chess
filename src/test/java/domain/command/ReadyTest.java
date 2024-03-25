package domain.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("준비 상태에서 움직임 상태로 넘어가면 예외가 발생한다")
    void moveToStart() {
        assertThatThrownBy(() -> Ready.getInstance().next(Move.getInstance()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("준비 상태에서 움직임 상태로 넘어갈 수 없습니다");
    }

}
