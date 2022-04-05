package refactorChess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("체스 판을 생성할 수 있다.")
    void createChessBoard() {
        final ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initChessBoard());

        assertThat(chessBoard.getBoard()).hasSize(64);
    }
}
