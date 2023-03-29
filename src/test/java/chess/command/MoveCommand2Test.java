package chess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveCommand2Test {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        MoveCommand2 moveCommand2 = new MoveCommand2(List.of("a2", "a3"));
        assertEquals(moveCommand2.getType(), CommandType.MOVE);
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand2(List.of("a1")));
    }
    
}