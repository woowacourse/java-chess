package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
        Piece expected = PieceFactory.createPiece(name, Team.WHITE);
        Position position = Position.of(1, 2);
        pieceDao.add(expected, position);
        Piece piece = pieceDao.getByName(name);
        assertThat(piece).isEqualTo(expected);
    }

    @Test
    void update() {
        String name = "p";
        Piece expected = PieceFactory.createPiece(name, Team.WHITE);
        Position position = Position.of(1, 2);
        pieceDao.add(expected, position);
        Position to = Position.of(1, 4);
        pieceDao.update(expected, position, to);
        Piece retrievedPiece = pieceDao.getByName(name);
        assertThat(retrievedPiece).isEqualTo(expected);
    }

    @Test
    void getAll() {
        Piece pawn = new Piece(Team.WHITE, "p", null, null);
        Piece rook = new Piece(Team.WHITE, "r", null, null);
        pieceDao.add(pawn, Position.of(1, 2));
        pieceDao.add(rook, Position.of(1, 1));
        Map<Position, Piece> pieces = pieceDao.getAll();
        assertThat(pieces).hasSize(2);
    }
}