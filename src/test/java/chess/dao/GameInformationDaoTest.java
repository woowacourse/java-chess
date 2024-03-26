package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.GameInformation;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameInformationDaoTest implements DaoTest {
    private GameInformationDao gameInformationDao;

    @BeforeEach
    void initializeChessGameDao() {
        gameInformationDao = new GameInformationDao();
    }

    @DisplayName("데이터베이스에서 전체 데이터를 조회한다.")
    @Test
    void findAll() {
        // when
        List<GameInformation> dtos = gameInformationDao.findAll();

        // then
        assertThat(dtos.size()).isEqualTo(1);
    }
}
