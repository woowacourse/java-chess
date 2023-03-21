package chess.command;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartCommandTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        StartCommand startCommand = new StartCommand(List.of());
        assertThat(startCommand.isNotEnd()).isTrue();
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new StartCommand(List.of("a1")));
    }
    
}