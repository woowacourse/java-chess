package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardFactoryTest {
    @Test
    void create() {
        assertThat(BoardFactory.create()).isInstanceOf(Board.class);
    }
}