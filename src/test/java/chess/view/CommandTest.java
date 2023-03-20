package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {


    @Test
    @DisplayName("isFinish() : end 명령어가 들어오면 true를 반환한다.")
    void test_isFinish() throws Exception {
        //given
        String command1 = "end";
        String command2 = "move";

        //when & then
        assertTrue(Command.isEnd(command1));
        assertFalse(Command.isEnd(command2));
    }

    @Test
    @DisplayName("isMove() : move 명령어가 들어오면 true를 반환한다.")
    void test_isMove() throws Exception {
        //given
        String command1 = "end";
        String command2 = "move";

        //when & then
        assertTrue(Command.isMove(command2));
        assertFalse(Command.isMove(command1));
    }

    @Test
    @DisplayName("isNotStart() : start 명령어가 들어오지 않으면 true를 반환한다.")
    void test_isNotStart() throws Exception {
        //given
        String command1 = "start";
        String command2 = "move";

        //when & then
        assertTrue(Command.isNotStart(command2));
        assertFalse(Command.isNotStart(command1));
    }
}
