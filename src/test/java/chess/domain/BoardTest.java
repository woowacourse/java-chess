package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("체스판은 64개의 칸으로 이루어져 있다.")
    void board64Size() {
        final Board board = Board.create(new HashMap<>());

        assertThat(board.getBoard().size()).isEqualTo(64);
    }

}
