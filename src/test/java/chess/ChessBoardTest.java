package chess;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @ParameterizedTest
    @DisplayName("체스 초기 기물 배치 확인")
    @CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
    void pieceLocationCheck(int i, int j, String value) {
        assertThat(chessBoard.getChessBoard().get(i).get(j).getName()).isEqualTo(value);
    }

    @Test
    void movePawnSuccess() {
        chessBoard.move("b2", "b3");
        assertThat(chessBoard.getChessBoard().get(5).get(1).getName()).isEqualTo("p");
    }

    @Test
    void movePawnStart() {
        chessBoard.move("b2", "b4");
        assertThat(chessBoard.getChessBoard().get(4).get(1).getName()).isEqualTo("p");
    }

    @Test
    void attackPawnSuccess() {
        chessBoard.move("c7", "c5");
        chessBoard.move("c5", "c4");
        chessBoard.move("c4", "c3");
        chessBoard.move("b2", "c3");
        assertThat(chessBoard.getChessBoard().get(5).get(2).getName()).isEqualTo("p");
    }

    @Test
    void attackPawnFail() {
        chessBoard.move("c7", "c5");
        chessBoard.move("c5", "c4");
        chessBoard.move("c4", "c3");
        assertThatThrownBy(() -> {
            chessBoard.move("c2", "c3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void movePawnFailLinear() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void movePawnFailDiagonal() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "c3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveRookSuccess() {
        chessBoard.move("a2", "a3");
        chessBoard.move("a1", "a2");
        assertThat(chessBoard.getChessBoard().get(6).get(0).getName()).isEqualTo("r");
    }

    @Test
    void moveRookFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("a1", "a2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void attackRookSuccess() {
        chessBoard.move("a2", "a4");
        chessBoard.move("a1", "a3");
        chessBoard.move("a3", "b3");
        chessBoard.move("b3", "b7");
        assertThat(chessBoard.getChessBoard().get(1).get(1).getName()).isEqualTo("r");
    }

    @Test
    void moveBishopSuccess() {
        chessBoard.move("b2", "b3");
        chessBoard.move("c1", "b2");
        assertThat(chessBoard.getChessBoard().get(6).get(1).getName()).isEqualTo("b");
    }

    @Test
    void moveBishopFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("c1", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void attackBishopSuccess() {
        chessBoard.move("b2", "b3");
        chessBoard.move("c1", "a3");
        chessBoard.move("a3", "e7");
        assertThat(chessBoard.getChessBoard().get(1).get(4).getName()).isEqualTo("b");
    }

    @Test
    void moveKnightSuccess() {
        chessBoard.move("b1", "a3");
        assertThat(chessBoard.getChessBoard().get(5).get(0).getName()).isEqualTo("n");
    }

    @Test
    void moveKnightFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("b1", "d2");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
