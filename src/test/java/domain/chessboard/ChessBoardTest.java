package domain.chessboard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("체스판을 생성한다")
    @Test
    void generateChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        Assertions.assertThat(chessBoard.size()).isEqualTo(8);
    }
}
