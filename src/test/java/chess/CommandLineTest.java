package chess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.CommandLine;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandLineTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        assertDoesNotThrow(() -> new CommandLine(List.of("start")));
        assertDoesNotThrow(() -> new CommandLine(List.of("end")));
        assertDoesNotThrow(() -> new CommandLine(List.of("move", "a1", "b2")));
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new CommandLine(List.of("start", "a1")));
        assertThrows(IllegalArgumentException.class, () -> new CommandLine(List.of("end", "a1")));
        assertThrows(IllegalArgumentException.class, () -> new CommandLine(List.of("move")));
        assertThrows(IllegalArgumentException.class, () -> new CommandLine(List.of("move", "a1", "b2", "c3")));
    }
}
