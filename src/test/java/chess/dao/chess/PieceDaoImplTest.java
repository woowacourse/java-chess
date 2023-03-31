package chess.dao.chess;

import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceDaoImplTest {
    @Test
    @DisplayName("체스 게임 아이디를 기준으로 체스말 리스트의 정보를 조회한다.")
    void findByChessGameId() {
        // given
        final PieceDao pieceDao = new MockPieceDao();
        final long chessGameId = 1L;
        final List<PieceEntity> pieceEntities = PieceEntityHelper.createPieceEntities(chessGameId);
        pieceEntities.forEach(pieceDao::insert);

        // when
        final List<PieceEntity> findChessEntities = pieceDao.findByChessGameId(chessGameId);

        // then
        final List<Long> actual = getPieceEntityIds(findChessEntities);
        final List<Long> expected = getPieceEntityIds(pieceEntities);

        assertThat(actual)
                .isEqualTo(expected);

        assertThat(actual.size())
                .isSameAs(18);
    }

    @Test
    @DisplayName("체스 게임에 해당하는 체스말의 정보를 저장한다")
    void insert() {
        // given
        final PieceDao pieceDao = new MockPieceDao();

        // when
        final Long savedPieceId = pieceDao.insert(PieceEntity.createWithChessGameId(1L, 1, 2,
                "PAWN", "WHITE"));

        // then
        assertThat(savedPieceId)
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("입력으로 들어온 체스 게임에 대해 특정 위치에 존재하는 체스말의 정보를 제거한다")
    void deleteByPositions() {
        // given
        final PieceDao pieceDao = new MockPieceDao();
        final long chessGameId = 1L;
        final PieceEntity entity1 = PieceEntity.createWithChessGameId(chessGameId, 1, 1, "PAWN", "WHITE");
        final PieceEntity entity2 = PieceEntity.createWithChessGameId(chessGameId, 1, 1, "PAWN", "WHITE");
        pieceDao.insert(entity1);
        pieceDao.insert(entity2);

        // when
        pieceDao.deleteByPositions(chessGameId, entity1, entity2);

        // then
        assertThat(pieceDao.findByChessGameId(1L))
                .isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("체스 게임 아이디에 해당하는 체스말 정보를 제거한다")
    void deleteByChessGameId() {
        // given
        final PieceDao pieceDao = new MockPieceDao();
        final long chessGameId = 1L;
        final PieceEntity entity1 = PieceEntity.createWithChessGameId(chessGameId, 1, 1, "PAWN", "WHITE");
        final PieceEntity entity2 = PieceEntity.createWithChessGameId(chessGameId, 1, 1, "PAWN", "WHITE");
        pieceDao.insert(entity1);
        pieceDao.insert(entity2);

        // when
        pieceDao.deleteByChessGameId(chessGameId);

        // then
        assertThat(pieceDao.findByChessGameId(1L))
                .isEqualTo(Collections.emptyList());
    }

    private List<Long> getPieceEntityIds(final List<PieceEntity> pieceEntities) {
        return pieceEntities.stream()
                .map(PieceEntity::getId)
                .collect(Collectors.toUnmodifiableList());
    }
}
