package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    @Test
    @DisplayName("보드판의 기물들로 DB를 최신화한다.")
    void updatePieces() {
        Board board = Board.create();
        PieceDao.updatePieces(board);
    }

    @Test
    @DisplayName("DB에 저장된 모든 기물을 가져온다.")
    void findAll() {
        assertThat(PieceDao.findAll()).isNotEmpty();
    }
}
