package chess.domain.board;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardSerializerTest {

    @Test
    void serialize() {
        Board board = Board.initiaize();
        Map<String, String> serialized = BoardSerializer.serialize(board);
        assertThat(serialized.get("11")).isEqualTo("r");
    }
}