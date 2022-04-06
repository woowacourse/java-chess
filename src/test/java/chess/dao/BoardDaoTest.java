package chess.dao;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    void addPiece() {
        BoardDao boardDao = new BoardDao();
//        boardDao.addPiece(Location.of("a1"), new Bishop(Team.WHITE));
    }

    @Test
    void updatePiece() {
        BoardDao boardDao = new BoardDao();
        boardDao.updatePiece("a1", new King(Team.BLACK));
        Piece result = boardDao.getPiece("a1");
        Assertions.assertThat(result).isInstanceOf(King.class);
    }
}
