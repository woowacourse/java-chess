package dao;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.TeamColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameDaoTest {
    final GameDao gameDao = GameDao.getInstance();
    final PieceDao pieceDao = PieceDao.getInstance();

    @BeforeEach
    void setUp() {
        pieceDao.removeAllPieces();
        gameDao.removeAllGames();
        gameDao.addGame();
        gameDao.addGame();
    }

    @AfterEach
    void tearDown() {
        pieceDao.removeAllPieces();
        gameDao.removeAllGames();
    }

    @Test
    @DisplayName("현재 생성된 게임의 총 개수를 조회한다.")
    void tupleCountTest() {
        int count = gameDao.tupleCount();
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("게임 정보를 추가하면 자동 생성된 게임 ID를 반환한다.")
    void addGameTest() {
        int gameId = gameDao.addGame();

        assertThat(gameDao.findTurn(gameId)).isEqualTo(TeamColor.WHITE);
        assertThat(gameId).isEqualTo(3);
    }

    @Test
    @DisplayName("게임 ID로 저장된 차례를 조회한다.")
    void findTurnTest() {
        TeamColor turn = gameDao.findTurn(1);

        assertThat(turn).isEqualTo(TeamColor.WHITE);
    }

    @Test
    @DisplayName("가장 최근에 저장된 게임의 차례를 조회한다.")
    void latestGameTurnTest() {
        TeamColor turn = gameDao.findLatestGameTurn();

        assertThat(turn).isEqualTo(TeamColor.WHITE);
    }
}
