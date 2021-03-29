package chess.db.dao;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PlayerDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();

    @AfterEach
    void tearDown() throws SQLException {
        playerDAO.removeAll();
        chessGameDAO.removeAll();
    }

    @Test
    void saveAndFindById() throws SQLException {
        ChessGameEntity chessGame = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        Long gameId = chessGame.getId();

        playerDAO.save(WHITE, gameId);
        playerDAO.save(BLACK, gameId);

        Long whitePlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, BLACK);

        assertThat(whitePlayerId).isNotNull();
        assertThat(blackPlayerId).isNotNull();
    }

    @Test
    void remove() throws SQLException {
        ChessGameEntity chessGame = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        Long gameId = chessGame.getId();

        playerDAO.save(WHITE, gameId);
        playerDAO.save(BLACK, gameId);

        playerDAO.removeAllByChessGame(gameId);

        Long whitePlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, BLACK);

        assertThat(whitePlayerId).isNull();
        assertThat(blackPlayerId).isNull();
    }
}