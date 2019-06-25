package chess.model.board;

import chess.view.OutputView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    void 빈_보드_테스트() {
        Board board = new Board();
        board.initialize(new EmptyBoardInitializer());
        assertThat(OutputView.board(board))
                .isEqualTo(
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n");
    }

    @Test
    void 기본_보드_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        assertThat(OutputView.board(board))
                .isEqualTo(
                    "RNBQKBNR\n" +
                    "PPPPPPPP\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "pppppppp\n" +
                    "rnbqkbnr\n");
    }
}
