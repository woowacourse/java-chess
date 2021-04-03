package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.position.PositionDAO;
import chess.dao.position.PositionRepository;
import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionDAOTest {
    private final PositionRepository positionRepository = new PositionDAO();

    @DisplayName("모든 file, rank의 조합으로 이루어진 보드 칸 위치 정보를 가져올 수 있는지 테스트")
    @Test
    void findByFileAndRank() throws SQLException {
        for (File file : File.values()) {
            findByRanksInFileOf(file);
        }
    }

    private void findByRanksInFileOf(File file) throws SQLException {
        for (Rank rank : Rank.values()) {
            Position position = positionRepository.findByFileAndRank(file, rank);
            assertThat(position.getFile()).isSameAs(file);
            assertThat(position.getRank()).isSameAs(rank);
        }
    }
}