package chess.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartCommandTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        StartCommand startCommand = new StartCommand(List.of());
        Assertions.assertThat(startCommand.getType()).isEqualTo(CommandType.START);
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new StartCommand(List.of("a1")));
    }
    
}