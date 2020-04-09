package chess.domain.board;

import chess.domain.game.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    @Test
    @DisplayName("createBoard 테스트")
    void createBoard() {
        assertThat(BoardFactory.createBoard()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("createBlankBoard 테스트")
    void createBlankBoard() {
        assertThat(BoardFactory.createBlankBoard(new GameStatus())).isInstanceOf(Board.class);
    }
}
