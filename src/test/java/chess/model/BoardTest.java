package chess.model;

import chess.dto.BoardDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("체스판에 기물을 초기화 한다")
    @Test
    void createPiecesOnBoard() {
        Board board = Board.createInitialBoard();
        BoardDto boardDto = BoardDto.from(board);

        String expected = """
            RNBQKBNR
            PPPPPPPP
            ........
            ........
            ........
            ........
            pppppppp
            rnbqkbnr""";

        Assertions.assertThat(boardDto).hasToString(expected);
    }
}
