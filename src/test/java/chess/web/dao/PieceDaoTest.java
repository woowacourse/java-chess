package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.dto.PieceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    @DisplayName("기물을 저장한다.")
    @Test
    void 기물을_저장한다() {
        final MockPieceDao pieceDao = new MockPieceDao();

        pieceDao.save(new PieceDto("P", "a2", "black"));

        assertThat(pieceDao.getSize()).isEqualTo(1);
    }

    @DisplayName("기물 정보를 변경한다.")
    @Test
    void 기물_정보를_변경한다() {
        final MockPieceDao pieceDao = new MockPieceDao();
        PieceDto pieceDto = new PieceDto("p", "a2", "white");

        pieceDao.save(new PieceDto("P", "a2", "black"));
        pieceDao.update(pieceDto);

        assertThat(pieceDao.getPieceDtoByPosition("a2")).isEqualTo(pieceDto);
    }

    @DisplayName("기물을 전부 가져온다.")
    @Test
    void 기물을_전부_가져온다() {
        final MockPieceDao pieceDao = new MockPieceDao();

        pieceDao.save(new PieceDto("P", "a2", "black"));
        pieceDao.save(new PieceDto("p", "b2", "white"));

        assertThat(pieceDao.selectAll().size()).isEqualTo(2);
    }

    @DisplayName("기물을 전부 삭제한다.")
    @Test
    void 기물을_전부_삭제한다() {
        final MockPieceDao pieceDao = new MockPieceDao();

        pieceDao.save(new PieceDto("P", "a2", "black"));
        pieceDao.save(new PieceDto("p", "b2", "white"));
        pieceDao.deleteAll();

        assertThat(pieceDao.getSize()).isEqualTo(0);
    }
}
