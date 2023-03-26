package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.boardpieces.LocalBoardPiecesDao;
import org.junit.jupiter.api.Test;

class LocalBoardPiecesDaoTest {

    private final LocalBoardPiecesDao chessBoardDao = new LocalBoardPiecesDao();

    @Test
    void getConnection() {
        assertThat(chessBoardDao.getConnection()).isNotNull();
    }
}
