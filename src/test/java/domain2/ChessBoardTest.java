package domain2;

import chess.domain2.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    void chessBoardSizeCheck() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard().size()).isEqualTo(32);
    }
}
