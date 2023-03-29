package chess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandFactory2Test {
    
    @Test
    @DisplayName("명령 생성 테스트")
    void create_command_test() {
        Command2 command2 = CommandFactory2.generateCommand(List.of("start"));
        assertEquals(command2.getType(), CommandType.START);
        
        command2 = CommandFactory2.generateCommand(List.of("end"));
        assertEquals(command2.getType(), CommandType.END);
        
        command2 = CommandFactory2.generateCommand(List.of("status"));
        assertEquals(command2.getType(), CommandType.STATUS);
        
        command2 = CommandFactory2.generateCommand(List.of("move", "a2", "a3"));
        assertEquals(command2.getType(), CommandType.MOVE);
    }
    
}