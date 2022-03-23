package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    @DisplayName("특정 위치에 말의 종류를 조회한다.")
    void findPiece() {
        Board board = new Board();
        Assertions.assertThat(board.getPiece("a1").getSymbol()).isEqualTo("Rook");
    }
}
