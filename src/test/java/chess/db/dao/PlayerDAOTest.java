package chess.db.dao;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import java.sql.SQLException;
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
        ChessGameEntity chessRoomEntity = chessGameDAO.add(new ChessGameEntity(TEST_TITLE));
        PlayerEntity playerEntity = new PlayerEntity(WHITE, chessRoomEntity);

        PlayerEntity savedPlayerEntity = playerDAO.save(playerEntity);
        PlayerEntity foundByIdPlayerEntity = playerDAO.findById(savedPlayerEntity.getId());

        assertThat(savedPlayerEntity).isEqualTo(foundByIdPlayerEntity);
    }


    @Test
    void delete() throws SQLException {
        ChessGameEntity chessGameEntity = chessGameDAO.add(new ChessGameEntity(TEST_TITLE));
        PlayerEntity playerEntity = playerDAO.save(new PlayerEntity(BLACK, chessGameEntity));

        playerDAO.delete(playerEntity);

        PlayerEntity deletedPlayerEntity = playerDAO.findById(playerEntity.getId());
        assertThat(deletedPlayerEntity).isNull();
    }
}