package chess;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    @ParameterizedTest
    @DisplayName("체스 기물 배치 확인")
    @CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
    void pieceLocationCheck(int i, int j, String value) {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard().get(i).get(j).getName()).isEqualTo(value);
    }

    @Test
    void movePawnSuccess() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move("b2", "b3");
        assertThat(chessBoard.getChessBoard().get(6).get(2).getName()).isEqualTo("p");
    }

    @Test
    void movePawnFail() {
        ChessBoard chessBoard = new ChessBoard();
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveRookSuccess() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move("a2", "a3");
        chessBoard.move("a1", "a2");
        assertThat(chessBoard.getChessBoard().get(6).get(0).getName()).isEqualTo("r");
    }

    @Test
    void moveRookFail() {
        ChessBoard chessBoard = new ChessBoard();
        assertThatThrownBy(() -> {
            chessBoard.move("a1", "a2");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
