package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDao();
    @Test
    @DisplayName("DB 커넥션 테스트")
    void getConnectionTest() {
        try (final var connection = pieceDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("체스 기물 정보 저장 테스트")
    void saveNewGameTest() {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        pieceEntities.add(new PieceEntity("a1", "ROOK", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("b1", "KNIGHT", "WHITE", 1L));
        pieceEntities.add(new PieceEntity("c1", "BISHOP", "WHITE", 1L));
        pieceDao.saveNewGame(pieceEntities);
        //TODO 검증 추가
    }
}
