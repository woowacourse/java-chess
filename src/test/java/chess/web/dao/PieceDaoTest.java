package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.dto.PieceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    @DisplayName("기물을 저장한다.")
    @Test
    void 기물을_저장한다() {
        final PieceDaoForTest pieceDao = new PieceDaoForTest();

        pieceDao.save(new PieceDto("P", "a2", "black"));

        assertThat(pieceDao.getSize()).isEqualTo(1);
    }

    @DisplayName("기물을 전부 삭제한다.")
    @Test
    void 기물을_전부_삭제한다() {
        final PieceDaoForTest pieceDao = new PieceDaoForTest();

        pieceDao.save(new PieceDto("P", "a2", "black"));
        pieceDao.deleteAll();

        assertThat(pieceDao.getSize()).isEqualTo(0);
    }
}
