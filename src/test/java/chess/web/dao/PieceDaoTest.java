package chess.web.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.web.dto.PieceDto;

class PieceDaoTest {

    @Test
    void save() {
        final PieceDao pieceDao = new PieceDao();
        PieceDto pieceDto = PieceDto.of ("a1", "R", "images/r_black.png");
        assertThatCode(() -> {
            int savedId = pieceDao.save(pieceDto, 1);
            remove(savedId);
        }).doesNotThrowAnyException();
    }

    @Test
    void saveAll() {
        final PieceDao pieceDao = new PieceDao();
        List<PieceDto> pieces = new ArrayList<>();
        pieces.add(PieceDto.of("a1", "R", "images/r_black.png"));
        pieces.add(PieceDto.of("a2", "B", "images/b_black.png"));
        pieces.add(PieceDto.of("a3", "P", "images/p_black.png"));
        assertThatCode(() -> {
            List<Integer> ids = pieceDao.saveAll(pieces, 1);
            remove(ids);
        }).doesNotThrowAnyException();
    }

    @Test
    void findAll() throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        List<PieceDto> pieces = new ArrayList<>();
        PieceDto piece1 = PieceDto.of("a1", "R", "images/r_black.png");
        PieceDto piece2 = PieceDto.of("a2", "B", "images/b_black.png");
        PieceDto piece3 = PieceDto.of("a3", "P", "images/p_black.png");

        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        List<Integer> ids = pieceDao.saveAll(pieces, 1);
        List<PieceDto> findPieces = pieceDao.findAll();

        assertThat(findPieces).hasSize(3)
            .contains(piece1, piece2, piece3);
        remove(ids);
    }

    private void remove(int id) throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.remove(id);
    }

    private void remove(List<Integer> ids) throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        for (Integer id : ids) {
            pieceDao.remove(id);
        }
    }
}