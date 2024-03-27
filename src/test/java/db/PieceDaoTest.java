package db;

import domain.dto.PieceDto;
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
        PieceDto findPieceDto = pieceDao.findOne("A", "3");

        assertThat(findPieceDto).isEqualTo(pieceDto);
    }

    @Test
    void 피스를_찾는다() {
        final var pieceDto = new PieceDto("A", "3", "WHITE", "PAWN");
        pieceDao.add(pieceDto);

        PieceDto findPiece = pieceDao.findOne("A", "3");

        assertThat(findPiece.color()).isEqualTo("WHITE");
        assertThat(findPiece.type()).isEqualTo("PAWN");
    }

    @Test
    void 피스를_모두_찾는다() {
        final var pieceDtoA = new PieceDto("A", "3", "WHITE", "PAWN");
        final var pieceDtoB = new PieceDto("B", "5", "BLACK", "PAWN");
        final var pieceDtoC = new PieceDto("C", "7", "BLACK", "KING");
        pieceDao.add(pieceDtoA);
        pieceDao.add(pieceDtoB);
        pieceDao.add(pieceDtoC);

        assertThat(pieceDao.findAll())
                .containsExactlyInAnyOrder(pieceDtoA, pieceDtoB, pieceDtoC);
    }

    @Test
    void 저장된_피스_개수를_센다() {
        final var pieceDtoA = new PieceDto("A", "3", "WHITE", "PAWN");
        final var pieceDtoB = new PieceDto("B", "5", "BLACK", "PAWN");
        final var pieceDtoC = new PieceDto("C", "7", "BLACK", "KING");
        pieceDao.add(pieceDtoA);
        pieceDao.add(pieceDtoB);
        pieceDao.add(pieceDtoC);

        assertThat(pieceDao.hasRecords()).isTrue();
    }
}
