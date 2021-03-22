package chess.domain.gamestate;

import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameSetTest {
    private Board board;
    private State gameSet;

    @BeforeEach
    void setUp() {
        board = new Board(BoardInitializer.init());
        gameSet = new GameSet(board, Side.BLACK);
    }

    @Test
    @DisplayName("종료 상태로 바뀌는 finished 테스트")
    void finished() {
        assertThat(gameSet.finished()).isInstanceOf(Finished.class);
    }
}