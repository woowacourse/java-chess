package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class ChessBoardTest {

    @Nested
    @DisplayName("체스판 관련 테스트")
    class Construct {

        @DisplayName("체스판 생성 시, 포지션들이 잘 주입되었는 지")
        @Test
        void createBoard() {
            // given
            final int initBoardSize = 32;
            // when
            ChessBoard board = ChessBoard.createInitBoard();

            // then
            Assertions.assertThat(board.getPositionByPieceData()).hasSize(initBoardSize);
        }
    }
}
