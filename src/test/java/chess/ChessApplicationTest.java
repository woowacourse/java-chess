package chess;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

public class ChessApplicationTest extends NsTest {

    @Test
    @DisplayName("입력 명령어가 예외일 경우")
    void command_exception() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("not found command"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Command.NOT_FOUND_COMMAND_EXCEPTION));

    }

    @Test
    @DisplayName("입력 명령어가 start 경우")
    void command_start() {
        assertSimpleTest(() ->
            Assertions.assertDoesNotThrow(() -> runException("start"))
        );
    }

    @Test
    @DisplayName("입력 명령어가 end 경우")
    void command_end() {
        assertSimpleTest(() ->
            Assertions.assertDoesNotThrow(() -> runException("end"))
        );
    }

    @Override
    protected void runMain() {
        ChessApplication.main(new String[] {});
    }
}
