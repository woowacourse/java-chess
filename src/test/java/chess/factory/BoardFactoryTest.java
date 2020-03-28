package chess.factory;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    @Test
    @DisplayName("createBoard 테스트")
    void createBoard() {
        assertThat(BoardFactory.createBoard()).isInstanceOf(Board.class);
    }
}
