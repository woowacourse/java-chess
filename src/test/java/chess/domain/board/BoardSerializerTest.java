package chess.domain.board;

import chess.domain.UserInterface;
import chess.ui.Console;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardSerializerTest {

    @Test
    void serialize() {
        UserInterface userInterface = new Console();
        Board board = ChessBoard.initiaize(userInterface);
        Map<String, String> serialized = BoardSerializer.serialize(board);
        System.out.println(serialized);
        assertThat(serialized.get("11")).isEqualTo("r");
    }
}