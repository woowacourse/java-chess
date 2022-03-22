package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    @Test
    void init() {
        ChessBoard chessBoard = new ChessBoard();
        int actual = chessBoard.countPiece();
        int expected = 32;
        assertThat(actual).isEqualTo(expected);
    }
}