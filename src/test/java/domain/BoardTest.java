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
        board.initBoard();
    }

    @Test
    void 입력받은_위치에_말이_있는_경우_말_반환_확인() {
        assertThat(board.pieceValueOf("a1").toString()).isEqualTo("r");
    }

    @Test
    void 입력받은_위치에_말이_없는_경우() {
        assertThat(board.pieceValueOf("a3")).isEqualTo(null);
    }

    @Test
    void 팀이_잘_변경되는지_확인() {
        assertThat(board.switchTurn()).isEqualTo(Aliance.BLACK);
        assertThat(board.switchTurn()).isEqualTo(Aliance.WHITE);
    }

    @Test
    void 입력받은_위치가_유효한_위치가_아닌_경우_예외_반환(){
        assertThrows(IllegalArgumentException.class, () ->board.movePiece("k3","a1"));
    }

    @Test
    void 입력받은_위치에_말이_없는_경우_예외_반환() {
        assertThrows(IllegalArgumentException.class, () -> board.movePiece("a4","a5"));
    }

    @Test
    void 같은_팀이_아닌_경우_예외_반환() {
        assertThrows(IllegalArgumentException.class, () -> board.movePiece("a7","a6"));
    }

    @Test
    void 폰_잘못된_방향인_경우_예외_반환() {
        assertThrows(IllegalArgumentException.class, () -> board.movePiece("a2","b4"));
    }

    @Test
    void 폰_이동횟수_초과한_경우_예외_반환() {
        assertThrows(IllegalArgumentException.class, () -> board.movePiece("a2","a5"));
    }

    @Test
    void 폰_이동_확인() {
        board.movePiece("a2", "a3");
        assertThat(board.pieceValueOf("a3").toString()).isEqualTo("p");
    }

    @Test
    void 폰_처음_이동시_두칸_이동이_가능한지_확인() {
        board.movePiece("a2", "a4");
        assertThat(board.pieceValueOf("a4").toString()).isEqualTo("p");
    }

    @Test
    void 나이트_이동_확인() {
        board.movePiece("b1", "c3");
        assertThat(board.pieceValueOf("c3").toString()).isEqualTo("n");
    }

    @Test
    void 도착지에_같은팀_말이_있는_경우_예외_반환() {
        assertThrows(IllegalArgumentException.class, ()->board.movePiece("b1", "d2"));
    }

    @Test
    void 도착지에_다른팀_말이_있는_경우_확인() {
        board.movePiece("b1","c3");
        board.movePiece("c3","d5");
        board.movePiece("d5","e7");
        assertThat(board.pieceValueOf("e7").toString()).isEqualTo("n");
    }

    @Test
    void 왕이_있는_경우() {
        assertThat(board.isKingAlive(Aliance.WHITE)).isEqualTo(true);
    }

    @Test
    void 왕이_없는_경우() {
        Board emptyBoard = new Board();
        assertThat(emptyBoard.isKingAlive(Aliance.WHITE)).isEqualTo(false);
    }
}
