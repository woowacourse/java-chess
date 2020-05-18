package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceDaoTest {

    @Test
    void add() throws SQLException, ClassNotFoundException {
        PieceDao pieceDao = new PieceDao();
        Piece pieceToAdd = new Piece(Team.WHITE, "p", null, null);
        String id = "1";
        pieceToAdd.setId(id);
        pieceDao.add(pieceToAdd);
        Piece retrievedPiece = pieceDao.get(id);
        assertThat(retrievedPiece).isEqualTo(pieceToAdd);
    }
}