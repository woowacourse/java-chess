package chess.view;

import chess.model.board.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleOutputTest {
    Board board;

    @Test
    void board() {
        board = Board.makeInitialBoard();
        final String expected =
                "RNBQKBNR\n" +
                        "PPPPPPPP\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "pppppppp\n" +
                        "rnbqkbnr\n";
        final String result = ConsoleOutput.getBoardString(board);
        assertThat(result.equals(expected)).isTrue();
    }
}