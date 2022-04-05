package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    @Test
    @DisplayName("보드판의 기물들로 DB를 최신화한다.")
    void updatePieces() {
        PieceDao pieceDao = new PieceDao();
        Board board = new Board();
        pieceDao.updatePieces(board);
    }

    @Test
    @DisplayName("DB에 저장된 모든 기물을 가져온다.")
    void findAll() {
        PieceDao pieceDao = new PieceDao();
        Board board = new Board();
        List<Piece> pieces = pieceDao.findAll(board);
        assertThat(pieces).isNotEmpty();
    }
}
