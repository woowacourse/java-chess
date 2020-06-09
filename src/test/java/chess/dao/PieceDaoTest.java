package chess.dao;

import chess.domain.dto.PieceDto;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    private static final PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
    private static final PieceDao pieceDao = pieceDaoFactory.createPieceDao();

    @BeforeEach
    void setUp() {
        pieceDao.deleteAll();
    }

    @AfterEach
    void clean() {
        pieceDao.deleteAll();
    }

    @Test
    void add() {
        PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
        PieceDao pieceDao = pieceDaoFactory.createPieceDao();
        Piece pieceToAdd = new Piece(Team.WHITE, "p", null, null);
        String id = "1";
        pieceToAdd.setId(id);
        Position position = Position.of(1, 2);
        pieceDao.add(pieceToAdd, position);
        PieceDto retrievedPieceDto = pieceDao.get(id);
        assertThat(retrievedPieceDto.getId()).isEqualTo(id);
        assertThat(retrievedPieceDto.getName()).isEqualTo(pieceToAdd.getName());
        assertThat(retrievedPieceDto.getTeam()).isEqualTo(pieceToAdd.getTeam().toString());
        assertThat(retrievedPieceDto.getPosition()).isEqualTo(position.toString());

    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
        PieceDao pieceDao = pieceDaoFactory.createPieceDao();
        Piece piece = new Piece(Team.WHITE, "p", null, null);
        String id = "1";
        piece.setId(id);
        Position position = Position.of(1, 2);
        pieceDao.add(piece, position);
        Position to = Position.of(1,4);
        pieceDao.update(piece, to);
        PieceDto retrievedPieceDto = pieceDao.get(id);
        assertThat(retrievedPieceDto.getPosition()).isEqualTo(to.toString());
    }
}