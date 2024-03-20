package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void 보드는_64개의_칸으로_구성한다() {
        // given
        Board board = new Board(new HashMap<>());

        // when, then
        assertThat(board.getSignatures().size()).isEqualTo(64);
    }
}
