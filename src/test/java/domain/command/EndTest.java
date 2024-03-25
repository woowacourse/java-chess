package domain.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("끝난 상태에서 다음 상태로 넘어가면 예외가 발생한다")
    void moveToStart() {
        assertThatThrownBy(() -> End.getInstance().next(End.getInstance()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("끝난 상태에서 다음 상태로 넘어갈 수 없습니다");
    }

}
