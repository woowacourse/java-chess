package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessGameEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ChessRoomDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final ChessGameDAO chessRoomDAO = new ChessGameDAO();

    @AfterEach
    void tearDown() throws SQLException {
        chessRoomDAO.deleteAll();
    }

    @Test
    void add() throws SQLException {
        ChessGameEntity chessRoomEntity = new ChessGameEntity(TEST_TITLE);

        chessRoomDAO.add(chessRoomEntity);
    }

    @Test
    void findById() throws SQLException {
        ChessGameEntity chessRoom = new ChessGameEntity(TEST_TITLE);
        ChessGameEntity addedChessRoom = chessRoomDAO.add(chessRoom);

        ChessGameEntity foundChessRoom = chessRoomDAO.findById(addedChessRoom.getId());

        assertThat(foundChessRoom).isEqualTo(addedChessRoom);
    }

    @Test
    void delete() throws SQLException {
        ChessGameEntity chessRoomEntity = new ChessGameEntity(TEST_TITLE);
        chessRoomDAO.add(chessRoomEntity);

        chessRoomDAO.delete(chessRoomEntity);

        ChessGameEntity deletedChessRoom = chessRoomDAO.findById(chessRoomEntity.getId());

        assertThat(deletedChessRoom).isNull();
    }
}