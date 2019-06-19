package domain;

import chess.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void 입력받은_위치에_말이_있는_경우_말_반환_확인() {
        assertThat(board.pieceValueOf("a1").toString()).isEqualTo("r");
    }

    @Test
    void 입력받은_위치에_말이_없는_경우() {
        assertThat(board.pieceValueOf("c3")).isEqualTo(null);
    }

    @Test
    void 입력받은_위치가_유효한위치가_아닌경우(){
        assertThrows(IllegalArgumentException.class, () ->board.checkValidPosition("k3"));
    }
}
