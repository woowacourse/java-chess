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
        playerDAO.deleteAll();
        chessGameDAO.deleteAll();
    }

    @Test
    void saveAndFindById() throws SQLException {
        ChessGameEntity chessGameEntity = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        PlayerEntity playerEntity1 = new PlayerEntity(WHITE, chessGameEntity);
        PlayerEntity playerEntity2 = new PlayerEntity(BLACK, chessGameEntity);

        PlayerEntity savedPlayerEntity1 = playerDAO.save(playerEntity1);
        PlayerEntity savedPlayerEntity2 = playerDAO.save(playerEntity2);

        List<PlayerEntity> foundPlayers = playerDAO.findAllByChessGame(chessGameEntity);

        assertThat(foundPlayers).containsExactlyInAnyOrder(
            savedPlayerEntity1, savedPlayerEntity2
        );
    }

    @Test
    void delete() throws SQLException {
        ChessGameEntity chessGameEntity = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        playerDAO.save(new PlayerEntity(BLACK, chessGameEntity));
        playerDAO.save(new PlayerEntity(WHITE, chessGameEntity));

        playerDAO.deleteAll();

        List<PlayerEntity> foundPlayers = playerDAO.findAllByChessGame(chessGameEntity);

        assertThat(foundPlayers).isEmpty();
    }
}