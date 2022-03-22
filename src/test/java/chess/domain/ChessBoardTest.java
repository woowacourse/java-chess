package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("체스판 생성")
    void createNewChessBoard() {
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        assertThat(chessBoard.getPieces()).hasSize(32);
    }
}