package chess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandFactoryTest {
    
    @Test
    @DisplayName("명령 생성 테스트")
    void create_command_test() {
        Command command = CommandFactory.generateCommand(List.of("start"));
        assertEquals(command.getType(), CommandType.START);
        
        command = CommandFactory.generateCommand(List.of("end"));
        assertEquals(command.getType(), CommandType.END);
        
        command = CommandFactory.generateCommand(List.of("status"));
        assertEquals(command.getType(), CommandType.STATUS);
        
        command = CommandFactory.generateCommand(List.of("move", "a2", "a3"));
        assertEquals(command.getType(), CommandType.MOVE);
    }
    
}