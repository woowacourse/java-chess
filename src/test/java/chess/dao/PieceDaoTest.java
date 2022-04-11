package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Color;
import chess.domain.board.Pawn;
import chess.domain.board.Piece;
import chess.domain.position.Position;
import chess.web.dao.PieceDaoImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    PieceDaoImpl pieceDao = new PieceDaoImpl();

    @AfterEach
    void tearDown() {
        pieceDao.removeAll();
    }

    @Test
    void savePieces() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a2"), new Pawn(Color.WHITE));
        pieceDao.savePieces(value);

        assertThat(pieceDao.findAll().size()).isEqualTo(1);
    }

    @Test
    void findPieceByPosition() {
        Map<Position, Piece> value = new HashMap<>();
        Pawn piece = new Pawn(Color.WHITE);
        Position position = Position.of("a2");
        value.put(position, piece);
        pieceDao.savePieces(value);

        Piece findPiece = pieceDao.findPieceByPosition(position);
        assertThat(findPiece).isEqualTo(piece);
    }

    @Test
    void deletePiece() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a2"), new Pawn(Color.WHITE));
        pieceDao.savePieces(value);
        pieceDao.deletePiece(Position.of("a2"));

        assertThat(pieceDao.findAll().size()).isEqualTo(0);
    }

    @Test
    void updatePiecePosition() {
        Map<Position, Piece> value = new HashMap<>();
        Pawn piece = new Pawn(Color.WHITE);
        Position originPosition = Position.of("a2");
        Position newPosition = Position.of("a4");
        value.put(originPosition, piece);
        pieceDao.savePieces(value);

        pieceDao.updatePosition(originPosition, newPosition);
        Piece findPiece = pieceDao.findPieceByPosition(newPosition);

        assertThat(findPiece).isEqualTo(piece);

    }
}
