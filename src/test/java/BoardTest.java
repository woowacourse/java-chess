import chess.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    void construct_board() {
        Board board = new Board();

        assertThat(board.getBoard().size()).isEqualTo(64);
    }
}
