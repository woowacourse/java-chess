package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MoveInfoTest {

    @Test
    void create_When_Success() {
        assertThat(MoveInfo.of("move a1 a2")).isInstanceOf(MoveInfo.class);
    }

    @Test
    void create_When_Fail_With_WrongPositionInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> MoveInfo.of("move a99 a10"))
                .withMessage("위치값을 잘못 입력하셨습니다.");
    }
}