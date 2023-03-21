package chess.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveCommandTest {
    
    @Test
    @DisplayName("move 명령 생성 테스트")
    void create_move_command_test() {
        MoveCommand moveCommand = new MoveCommand(List.of("a1", "b2"));
        assertThat(moveCommand.getFrom()).isEqualTo(Position.from("a1"));
        assertThat(moveCommand.getTo()).isEqualTo(Position.from("b2"));
    }
    
    @Test
    @DisplayName("move 명령 생성 에러 테스트")
    void create_move_command_error_test() {
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(List.of("a1")));
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(List.of("a1", "b2", "c3")));
    }
    
}