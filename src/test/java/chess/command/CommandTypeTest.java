package chess.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTypeTest {
    
    @Test
    @DisplayName("명령타입 정상 생성 테스트")
    void create_command_type_test() {
        assertThat(CommandType.from("start")).isEqualTo(CommandType.START);
        assertThat(CommandType.from("end")).isEqualTo(CommandType.END);
        assertThat(CommandType.from("move")).isEqualTo(CommandType.MOVE);
    }
    
    @Test
    @DisplayName("명령타입 생성 에러 테스트")
    void create_error_command_type() {
        assertThrows(IllegalArgumentException.class, () -> CommandType.from("a1"));
    }
}