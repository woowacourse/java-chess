package chess.domain.database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    public void connection() {
        assertThat(chessGameDao.getConnection()).isNotNull();
    }
}
