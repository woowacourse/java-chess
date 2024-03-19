package chess.model;

import chess.dto.BoardDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("기물을 배치한다")
    @Test
    void createPiecesOnBoard() {
        Board board = new Board();
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
