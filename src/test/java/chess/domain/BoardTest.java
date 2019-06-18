package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BoardTest {
    @Test
    void 체크판_생성() {
        assertDoesNotThrow(() -> new Board());
    }

    @Test
    void 체크판_초기화_확인() {
        Board board = new Board();
        assertThat(board.getPieces().get(new Point("h1"))).isEqualTo(new Rook(true));
    }
}
