package chess;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dao.PieceDao;
import chess.model.piece.Piece;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    @Test
    @DisplayName("체스판이 db에 저장되었는지 확인한다")
    void init() {
        PieceDao pieceDao = new PieceDao();
        Board board = BoardFactory.create();
        pieceDao.init(board);

        Map<Position, Piece> boardMap = pieceDao.findAll();

        assertThat(boardMap.size()).isEqualTo(64);
    }

    @Test
    @DisplayName("체스판이 db에 저장되었는지 확인한다")
    void findByPosition() {
        PieceDao pieceDao = new PieceDao();
        Board board = BoardFactory.create();
        pieceDao.init(board);

        String pieceName = pieceDao.findByPosition("a2");

        assertThat(pieceName).isEqualTo("white-p");
    }

    @Test
    @DisplayName("체스판의 말을 update하는 것을 확인한다.")
    void updatePieceNameByPosition() {
        PieceDao pieceDao = new PieceDao();
        Board board = BoardFactory.create();
        pieceDao.init(board);

        pieceDao.updateByPosition("a2", "none-.");

        String pieceName = pieceDao.findByPosition("a2");

        assertThat(pieceName).isEqualTo("none-.");
    }

    @Test
    @DisplayName("체스판의 말을 모두 삭제한다.")
    void deleteAll() {
        PieceDao pieceDao = new PieceDao();
        Board board = BoardFactory.create();
        pieceDao.init(board);

        pieceDao.deleteAll();

        Map<Position, Piece> boardMap = pieceDao.findAll();
        assertThat(boardMap.size()).isZero();
    }
}
