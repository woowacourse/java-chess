package chess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndCommandTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        EndCommand endCommand2 = new EndCommand(List.of());
        assertEquals(endCommand2.getType(), CommandType.END);
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new EndCommand(List.of("a1")));
    }
}