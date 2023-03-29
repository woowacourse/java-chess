package chess.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartCommand2Test {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        StartCommand2 startCommand2 = new StartCommand2(List.of());
        Assertions.assertThat(startCommand2.getType()).isEqualTo(CommandType.START);
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> new StartCommand2(List.of("a1")));
    }
    
}