package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    void remove() {
        final BoardDao boardDao = new BoardDao();
        boardDao.removeBoard();
        assertThat(boardDao.getBoard().getBoard()).hasSize(0);
    }
}
