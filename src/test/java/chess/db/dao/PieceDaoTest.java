package chess.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PieceDaoTest {

    @Test
    @DisplayName("피스 저장")
    void saveWithRollback() {
        PieceDao pieceDao = new PieceDao();

        assertThatCode(() -> pieceDao.save("a1", "♙", 1))
                .doesNotThrowAnyException();

        pieceDao.delete(1);
    }

    @Test
    @DisplayName("피스 조회")
    void findByChessGameId() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("a1", "♙", 1);

        assertThatCode(() -> pieceDao.findByChessGameId(1))
                .doesNotThrowAnyException();

        pieceDao.delete(1);
    }

    @Test
    @DisplayName("피스 삭제")
    void delete() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("a1", "♙", 1);

        assertThatCode(() -> pieceDao.delete(1))
                .doesNotThrowAnyException();
    }
}
