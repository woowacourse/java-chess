package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    @Test
    void add() throws SQLException, ClassNotFoundException {
        PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
        Piece pieceToAdd = new Piece(Team.WHITE, "p", null, null);
        PieceDao pieceDao = pieceDaoFactory.createPieceDaoAdd(pieceToAdd);
        String id = "1";
        pieceToAdd.setId(id);
        pieceDao.add(pieceToAdd);
        pieceDao = pieceDaoFactory.createPieceDaoGet(id);
        Piece retrievedPiece = pieceDao.get(id);
        assertThat(retrievedPiece).isEqualTo(pieceToAdd);
    }
}