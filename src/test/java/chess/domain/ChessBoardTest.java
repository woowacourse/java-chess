package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("체스판은 64개의 칸이있다.")
    void createBoard() {
        ChessBoard chessBoard = ChessBoard.getInstance();
        assertThat(chessBoard.getBoard()).asList().hasSize(64);
    }
}
