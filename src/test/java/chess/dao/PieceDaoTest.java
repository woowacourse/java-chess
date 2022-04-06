package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Color;
import chess.domain.board.Pawn;
import chess.domain.board.Piece;
import chess.domain.position.Position;
import chess.web.dao.PieceDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    PieceDao pieceDao = new PieceDao();

    @AfterEach
    void tearDown() {
        pieceDao.removeAll();
    }

    @Test
    void savePieces() {
        Board board = Board.getInitializedInstance();
        pieceDao.savePieces(board.getValue());

        assertThat(pieceDao.findAll().size()).isEqualTo(32);
    }

    @Test
    void findPieceByPosition() {
        Board board = Board.getInitializedInstance();
        pieceDao.savePieces(board.getValue());

        Piece piece = pieceDao.findPieceByPosition(Position.of("a2"));
        assertThat(piece).isEqualTo(new Pawn(Color.WHITE));
    }

}
