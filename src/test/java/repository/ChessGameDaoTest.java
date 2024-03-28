package repository;

import connection.ChessConnectionGenerator;
import domain.ChessGameStatus;
import domain.Team;
import domain.player.Player;
import domain.player.PlayerName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessGameDaoTest {

    final Connection connection = ChessConnectionGenerator.getTestConnection();
    final ChessGameDao chessGameDao = new ChessGameDao(connection);
    final PlayerDao playerDao = new PlayerDao(connection);
    final PlayerName pobi = new PlayerName("pobi");
    final PlayerName json = new PlayerName("json");

    @BeforeEach
    void before() {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
                playerDao.add(pobi);
                playerDao.add(json);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void after() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("게임을 추가하고 찾는다.")
    @Test
    void add() {
        // given
        final Player blackPlayer = new Player(pobi);
        final Player whitePlayer = new Player(json);
        final Team team = Team.WHITE;
        final ChessGameStatus status = ChessGameStatus.RUNNING;

        // when
        final int gameId = chessGameDao.addGame(blackPlayer, whitePlayer, team, status);

        // then
        final Team currentTeam = chessGameDao.findCurrentTeamById(gameId).get();
        final ChessGameStatus chessGameStatus = chessGameDao.findStatusById(gameId).get();

        assertAll(
                () -> assertThat(currentTeam).isEqualTo(team),
                () -> assertThat(chessGameStatus).isEqualTo(status)
        );
    }

    @DisplayName("게임의 현재 팀 변경을 업데이트 한다.")
    @Test
    void updateCurrentTeam() {
        // given
        final Player blackPlayer = new Player(pobi);
        final Player whitePlayer = new Player(json);
        final int gameId = chessGameDao.addGame(blackPlayer, whitePlayer, Team.WHITE, ChessGameStatus.RUNNING);

        // when
        final Team team = Team.BLACK;
        chessGameDao.updateCurrentTeam(gameId, team);

        // then
        final Team findTeam = chessGameDao.findCurrentTeamById(gameId).get();

        assertThat(findTeam).isEqualTo(team);
    }

    @DisplayName("게임의 진행 상태를 업데이트 한다.")
    @Test
    void updateStatus() {
        // given
        final Player blackPlayer = new Player(pobi);
        final Player whitePlayer = new Player(json);
        final int gameId = chessGameDao.addGame(blackPlayer, whitePlayer, Team.WHITE, ChessGameStatus.RUNNING);

        // when
        final ChessGameStatus status = ChessGameStatus.END;
        chessGameDao.updateStatusById(gameId, status);

        // then
        final ChessGameStatus findStatus = chessGameDao.findStatusById(gameId).get();

        assertThat(findStatus).isEqualTo(status);
    }
}
