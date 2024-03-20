package domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.ChessBoard;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {
    @DisplayName("체스보드가 생성되면 32개의 말이 셋팅된다")
    @Test
    void initialBoard() {
        // given
        ChessBoard chessBoard = new ChessBoard();

        // when
        chessBoard.initialBoard();

        // then
        assertThat(chessBoard).extracting("chessBoard")
                .satisfies(map -> assertThat((Map<?, ?>) map).hasSize(32));
    }
}
