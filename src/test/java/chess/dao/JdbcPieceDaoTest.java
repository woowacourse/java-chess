package chess.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcPieceDaoTest {

    private final JdbcPieceDao jdbcPieceDao = JdbcPieceDao.getInstance();

    @Test
    @DisplayName("최근 1개의 Piece ID를 조회한다.")
    void findRecentPieceId() {
        // when
        Long recentPieceId = jdbcPieceDao.findRecentPieceId();

        // then
        Assertions.assertThat(recentPieceId).isEqualTo(1028L);
    }
}
