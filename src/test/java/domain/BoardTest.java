package domain;

import chess.domain.*;
import chess.domain.piece.Piece;
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
    void 입력받은_위치가_유효한_위치가_아닌_경우(){
        assertThrows(IllegalArgumentException.class, () ->board.checkValidPosition("k3"));
    }

    @Test
    void 팀이_잘_변경되는지_확인() {
        assertThat(board.switchTurn()).isEqualTo(Aliance.BLACK);
    }

    @Test
    void 입력받은_위치에_말이_없는_경우_예외_반환_확인() {
        assertThrows(IllegalArgumentException.class, () -> board.checkOccupiedPosition("a3"));
    }

    @Test
    void 입력받은_위치에_말이_있는_경우_같은_팀인지_확인() {
        Piece piece = board.pieceValueOf("a7");
        assertThrows(IllegalArgumentException.class, () -> board.checkProperTeam(piece));
    }
}
