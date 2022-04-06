package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.view.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    void addPiece() {
        BoardDao boardDao = new BoardDao();
        boardDao.addPiece("a1", new Rook(Team.WHITE));
    }

    @Test
    void updatePiece() {
        BoardDao boardDao = new BoardDao();
        boardDao.updatePiece("a1", new King(Team.BLACK));
        Piece result = boardDao.getPiece("a1");
        Assertions.assertThat(result).isInstanceOf(King.class);
    }

    @Test
    void deleteAll() {
        BoardDao boardDao = new BoardDao();
        boardDao.deleteAll();
    }

    @Test
    void getBoardMap() {
        BoardDao boardDao = new BoardDao();
        Board board = new Board(boardDao.getBoardMap());
        OutputView.printChessBoard(board);
    }
}
