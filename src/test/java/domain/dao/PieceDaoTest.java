package domain.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceDaoTest {
    private final PieceDao pieceDao = new PieceDao();

    @AfterEach
    void rollback() {
        pieceDao.deleteAll();
    }

    @Test
    void 피스를_저장한다() {
        final var pieceDto = new PieceDto("A", "3", "WHITE", "PAWN");

        pieceDao.add(pieceDto);
        PieceDto findPieceDto = pieceDao.find("A", "3");

        assertThat(findPieceDto).isEqualTo(pieceDto);
    }

    @Test
    void 피스를_찾는다() {
        final var pieceDto = new PieceDto("A", "3", "WHITE", "PAWN");
        pieceDao.add(pieceDto);

        PieceDto findPiece = pieceDao.find("A", "3");

        assertThat(findPiece.color()).isEqualTo("WHITE");
        assertThat(findPiece.type()).isEqualTo("PAWN");
    }
}
