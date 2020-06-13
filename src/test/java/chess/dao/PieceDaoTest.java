package chess.dao;

import chess.domain.dto.PieceDto;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    private static final PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
    private static final PieceDao pieceDao = pieceDaoFactory.createPieceDao();

    @BeforeEach
    void setUp() {
        pieceDao.deleteAll();
    }

    @AfterAll
    static void clean() {
        pieceDao.deleteAll();
    }

    @Test
    void add() {
        String name = "p";
        Piece pieceToAdd = new Piece(Team.WHITE, name, null, null);
        Position position = Position.of(1, 2);
        pieceDao.add(pieceToAdd, position);
        PieceDto retrievedPieceDto = pieceDao.getByName(name);
        assertThat(retrievedPieceDto.getName()).isEqualTo(pieceToAdd.getName());
        assertThat(retrievedPieceDto.getTeam()).isEqualTo(pieceToAdd.getTeam().toString());
        assertThat(retrievedPieceDto.getPosition()).isEqualTo(position.toString());

    }

    @Test
    void update() {
        String name = "p";
        Piece piece = new Piece(Team.WHITE, name, null, null);
        Position position = Position.of(1, 2);
        pieceDao.add(piece, position);
        Position to = Position.of(1, 4);
        pieceDao.update(piece, position, to);
        PieceDto retrievedPieceDto = pieceDao.getByName(name);
        assertThat(retrievedPieceDto.getPosition()).isEqualTo(to.toString());
    }

    @Test
    void getAll() {
        Piece pawn = new Piece(Team.WHITE, "p", null, null);
        Piece rook = new Piece(Team.WHITE, "r", null, null);
        pieceDao.add(pawn, Position.of(1, 2));
        pieceDao.add(rook, Position.of(1, 1));
        List<PieceDto> pieceDtos = pieceDao.getAll();
        assertThat(pieceDtos).hasSize(2);
    }
}