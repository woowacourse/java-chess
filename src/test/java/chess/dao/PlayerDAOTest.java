package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessRoomEntity;
import chess.dao.entity.PlayerEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final ChessRoomDAO chessRoomDAO = new ChessRoomDAO();

    @AfterEach
    void tearDown() throws SQLException {
        playerDAO.deleteAll();
        chessRoomDAO.deleteAll();
    }

    @Test
    void add() throws SQLException {
        ChessRoomEntity chessRoomEntityToBeAdded = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
        PlayerEntity playerEntity = new PlayerEntity(chessRoomEntity);

        playerDAO.add(playerEntity);
    }

    @Test
    void findById() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(addedChessRoom);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);

        PlayerEntity foundPlayerEntity = playerDAO.findById(playerEntity.getId());

        assertThat(foundPlayerEntity).isEqualTo(playerEntity);
    }

    @Test
    void delete() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);
        PlayerEntity playerEntity = new PlayerEntity(addedChessRoom);
        playerDAO.add(playerEntity);

        playerDAO.delete(playerEntity);

        PlayerEntity deletedPlayerEntity = playerDAO.findById(playerEntity.getId());

        assertThat(deletedPlayerEntity).isNull();
    }
}