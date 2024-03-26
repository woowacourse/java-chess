package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.GameInformation;
import chess.domain.piece.Color;
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

    @DisplayName("gameId에 해당되는 게임 정보를 찾아온다.")
    @Test
    void findInformationByGameId() {
        // given
        int gameId = 1;

        // when
        GameInformation gameInformation = gameInformationDao.findByGameId(gameId);

        // then
        assertThat(gameInformation.getCurentTurnColor()).isEqualTo(Color.WHITE);
    }

    @DisplayName("현재 진행중인 팀의 색상을 전환한다.")
    @Test
    void updateTurn() {
        // given
        int gameId = 1;
        GameInformation gameInformation = new GameInformation(gameId, Color.BLACK);

        // when
        gameInformationDao.updateTurn(gameInformation);
        GameInformation updatedInformation = gameInformationDao.findByGameId(gameId);

        // then
        assertThat(gameInformation.getCurentTurnColor()).isEqualTo(Color.BLACK);
    }

    @DisplayName("gameId에 해당되는 게임을 삭제한다.")
    @Test
    void remove() {
        // given
        int gameId = 1;

        // when
        gameInformationDao.remove(1);
        List<GameInformation> gameInfos = gameInformationDao.findAll();

        // then
        assertThat(gameInfos).isEmpty();
    }

    @DisplayName("데이터베이스에 새로운 게임 데이터를 저장한다.")
    @Test
    void save() {
        // when
        gameInformationDao.create();
        List<GameInformation> gameInfos = gameInformationDao.findAll();

        // then
        assertThat(gameInfos.size()).isEqualTo(2);
    }
}
