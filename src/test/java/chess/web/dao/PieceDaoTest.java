package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.role.Bishop;
import chess.domain.piece.role.Pawn;
import chess.domain.position.Position;
import chess.web.dto.PieceDto;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    @Test
    void connection() {
        final var pieceDao = new PieceDao();
        final Connection connection = pieceDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void saveAll() {
        final var pieceDao = new PieceDao();
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of("a1"), new Piece(Color.WHITE, new Pawn()));
        pieces.put(Position.of("h8"), new Piece(Color.BLACK, new Bishop()));
        pieceDao.saveAll(pieces);
    }

    @Test
    void findAll() {
        final var pieceDao = new PieceDao();
        List<PieceDto> pieceDtos = pieceDao.findAll();
        assertThat(pieceDtos).isNotEmpty();
    }
}