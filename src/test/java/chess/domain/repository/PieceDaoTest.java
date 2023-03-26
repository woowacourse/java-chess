package chess.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.repository.entity.PieceEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDao();

    @BeforeEach
    void deleteAll() {
        pieceDao.deleteAll();
    }

    @Test
    @DisplayName("체스 기물 정보 저장 테스트")
    void saveNewGameTest() {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        pieceEntities.add(new PieceEntity("a1", "ROOK", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("b1", "KNIGHT", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("c1", "BISHOP", "WHITE", 1L));
        assertDoesNotThrow(() -> pieceDao.savePieces(pieceEntities));
    }

    @Test
    @DisplayName("체스판 정보 조회 테스트")
    void findAllPiecesTest() {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        pieceEntities.add(new PieceEntity("a1", "ROOK", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("b1", "KNIGHT", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("c1", "BISHOP", "WHITE", 1L));
        pieceDao.savePieces(pieceEntities);

        List<PieceEntity> allPieces = pieceDao.findAllPieces();
        assertThat(allPieces).hasSize(3);
    }
}
