package chess.domain.piece;

import chess.config.BoardConfig;
import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PiecesStateTest {
    @Test
    @DisplayName("#initialize() : should return initialized Board")
    void initiaize() {
        Board board = Board.initiaize();
        Map<String, String> serialized = board.serialize();
        assertThat(serialized.size()).isEqualTo(32);
        assertPawn(serialized, BoardConfig.LINE_START + 1, "p");
        assertPawn(serialized, BoardConfig.LINE_END - 1, "P");
        assertEdge(serialized, BoardConfig.LINE_START);
        assertEdge(serialized, BoardConfig.LINE_END);
    }

    private void assertPawn(Map<String, String> serialized, int row, String p) {
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            String position = String.valueOf(column) + String.valueOf(row);
            assertTrue(serialized.containsKey(position));
            String name = serialized.get(position);
            assertThat(name).isEqualTo(p);
        }
    }

    private void assertEdge(Map<String, String> serialized, int row) {
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            String position = String.valueOf(column) + String.valueOf(row);
            assertTrue(serialized.containsKey(position));
        }
    }
}