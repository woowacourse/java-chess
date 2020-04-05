package chess.generator;

import chess.domain.board.Board;
import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONGeneratorTest {
    @Test
    @DisplayName("generateJSON 테스트")
    void generateJSON() {
        Board board = BoardFactory.createBoard();

        assertThat(JSONGenerator.generateJSON(board)).isInstanceOf(String.class);
    }
}
