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


    @Test
    @DisplayName("위치로 기물 조회 테스트")
    void isExistByPositionTest() {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        String existPosition = "a1";
        pieceEntities.add(new PieceEntity(existPosition, "ROOK", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("b1", "KNIGHT", "WHITE", 1L));
        pieceDao.savePieces(pieceEntities);

        boolean existByPosition = pieceDao.isExistByPosition(existPosition);
        boolean notExistByPosition = pieceDao.isExistByPosition("b3");
        assertThat(existByPosition).isTrue();
        assertThat(notExistByPosition).isFalse();
    }

    @Test
    @DisplayName("위치로 삭제 테스트")
    void deleteByPositionTest() {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        String existPosition = "a1";
        pieceEntities.add(new PieceEntity(existPosition, "ROOK", "WHITE", 1L));
        pieceDao.savePieces(pieceEntities);

        boolean existByPosition = pieceDao.isExistByPosition(existPosition);
        assertThat(existByPosition).isTrue();

        pieceDao.deleteByPosition(existPosition);
        boolean afterDeleteExistByPosition = pieceDao.isExistByPosition(existPosition);
        assertThat(afterDeleteExistByPosition).isFalse();
    }

    @Test
    @DisplayName("위치 변경 테스트")
    void updatePiecePositionToTest() {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        String beforePosition = "a1";
        String afterPosition = "b3";
        pieceEntities.add(new PieceEntity(beforePosition, "ROOK", "WHITE", 1L));
        pieceDao.savePieces(pieceEntities);

        pieceDao.updatePiecePositionTo(beforePosition, afterPosition);

        PieceEntity pieceEntity = pieceDao.findByPosition(afterPosition);
        String position = pieceEntity.getPosition();
        assertThat(position).isEqualTo(afterPosition);
    }
}
