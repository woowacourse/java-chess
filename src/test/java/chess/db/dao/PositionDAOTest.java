package chess.db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.db.entity.PositionEntity;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionDAOTest {
    private final PositionDAO positionDAO = new PositionDAO();

    @DisplayName("모든 file, rank의 조합으로 이루어진 보드 칸 위치 정보를 가져올 수 있는지 테스트")
    @Test
    void findByFileAndRank() throws SQLException {
        for (File file : File.values()) {
            findPositionByRanksInFileOf(file);
        }
    }

    private void findPositionByRanksInFileOf(File file) throws SQLException {
        for (Rank rank : Rank.values()) {
            PositionEntity positionEntity = positionDAO.findByFileAndRank(file, rank);
            assertThat(positionEntity.getFile()).isEqualTo(file);
            assertThat(positionEntity.getRank()).isEqualTo(rank);
        }
    }
}