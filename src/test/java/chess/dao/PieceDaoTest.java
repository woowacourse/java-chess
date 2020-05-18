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
        PieceDao pieceDao = pieceDaoFactory.createPieceDao();
        Piece pieceToAdd = new Piece(Team.WHITE, "p", null, null);
        String id = "1";
        pieceToAdd.setId(id);

        pieceDao.add(pieceToAdd);
        Piece retrievedPiece = pieceDao.get(id);
        assertThat(retrievedPiece).isEqualTo(pieceToAdd);
    }
}