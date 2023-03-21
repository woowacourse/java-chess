package chess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandFactoryTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        assertDoesNotThrow(() -> CommandFactory.generateCommand(List.of("start")));
        assertDoesNotThrow(() -> CommandFactory.generateCommand(List.of("end")));
        assertDoesNotThrow(() -> CommandFactory.generateCommand(List.of("move", "a1", "b2")));
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.generateCommand(List.of("start", "a1")));
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.generateCommand(List.of("end", "a1")));
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.generateCommand(List.of("move")));
        assertThrows(IllegalArgumentException.class,
                () -> CommandFactory.generateCommand(List.of("move", "a1", "b2", "c3")));
    }
}