package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("체스보드 사이즈 확인")
    void chessBoardSizeCheck() {
        ChessBoard chessBoard = new ChessBoard();
        List<List<Piece>> board = chessBoard.getChessBoard();

        assertThat(board.size()).isEqualTo(8);
        for (List<Piece> pieces : board) {
            assertThat(pieces.size()).isEqualTo(8);
        }
    }
}
