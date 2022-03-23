package chess;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

public class ChessApplicationTest extends NsTest {

    private static final String NEXT_LINE = System.lineSeparator();

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

    @Test
    @DisplayName("체스판 초기화 출력 검증")
    void board() {
        assertSimpleTest(() -> {
            runException("start");
            assertThat(output()).contains(
                "RNBQKBNR" + NEXT_LINE
                    + "PPPPPPPP" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "........" + NEXT_LINE
                    + "pppppppp" + NEXT_LINE
                    + "rnbqkbnr");
        });
    }

    @Override
    protected void runMain() {
        ChessApplication.main(new String[] {});
    }
}
