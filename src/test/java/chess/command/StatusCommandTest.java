package chess.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusCommandTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        StatusCommand statusCommand2 = new StatusCommand(List.of());
        Assertions.assertThat(statusCommand2.getType()).isEqualTo(CommandType.STATUS);
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new StatusCommand(List.of("a1")));
    }
    
    
}