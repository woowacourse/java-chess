package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveCommandTest {

    @Test
    @DisplayName("commands는 addMoveCommand를 이용하여 추가될 수 있다.")
    void addMoveCommand() {
        MoveCommand moveCommand = new MoveCommand("abc");
        moveCommand.addMoveCommand("def");
        assertEquals("abcdef", moveCommand.getCommands());
    }

    @Test
    @DisplayName("commands는 4개씩 끊어서 리스트에 저장된다.")
    void interpretMoveCommands() {
        MoveCommand moveCommand = new MoveCommand("abcdefghijkl");
        List<String> commands = moveCommand.interpretMoveCommands();
        assertEquals(3, commands.size());
        assertEquals("abcd", commands.get(0));
    }

    @Test
    @DisplayName("commands는 초기화 할 수 있다.")
    void clear() {
        MoveCommand moveCommand = new MoveCommand("abcdefghijkl");
        moveCommand.clear();
        assertEquals("", moveCommand.getCommands());
    }
}
