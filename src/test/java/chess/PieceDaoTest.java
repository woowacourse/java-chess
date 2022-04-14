package chess;

import chess.model.board.BoardFactory;
import chess.model.dao.PieceDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    static PieceDao pieceDao;

    @BeforeEach
    void initPieceDaoTest() {
        pieceDao = new PieceDao();
        pieceDao.deleteAll();
        pieceDao.init(BoardFactory.create());
    }

    @AfterEach
    void cleanDB() {
        pieceDao.deleteAll();
    }

    @Test
    @DisplayName("체스판이 db에 저장되었는지 확인한다")
    void init() {
        Map<String, String> boardMap = pieceDao.findAll();

        assertThat(boardMap.size()).isEqualTo(64);
    }

    @Test
    @DisplayName("체스판이 db에 저장되었는지 확인한다")
    void findByPosition() {
        String pieceName = pieceDao.findByPosition("a2");

        assertThat(pieceName).isEqualTo("white-p");
    }

    @Test
    @DisplayName("체스판의 말을 update하는 것을 확인한다.")
    void updatePieceNameByPosition() {
        pieceDao.updateByPosition("a2", "none-.");

        String pieceName = pieceDao.findByPosition("a2");

        assertThat(pieceName).isEqualTo("none-.");
    }

    @Test
    @DisplayName("체스판의 말을 모두 삭제한다.")
    void deleteAll() {
        pieceDao.deleteAll();

        Map<String, String> boardMap = pieceDao.findAll();
        assertThat(boardMap.size()).isZero();
    }
}
