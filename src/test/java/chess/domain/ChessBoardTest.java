package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    void chessBoardSizeCheck2() {
        ChessBoard chessBoard = new ChessBoard();
        Map<Square, Piece> board = chessBoard.getChessBoard();
        assertThat(board.size()).isEqualTo(32);
    }
}
