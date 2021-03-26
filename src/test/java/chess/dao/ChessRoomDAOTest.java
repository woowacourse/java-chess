package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessRoomEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ChessRoomDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final ChessRoomDAO chessRoomDAO = new ChessRoomDAO();

    @AfterEach
    void tearDown() throws SQLException {
        chessRoomDAO.deleteAll();
    }

    @Test
    void add() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);

        chessRoomDAO.add(chessRoomEntity);
    }

    @Test
    void findById() throws SQLException {
        ChessRoomEntity chessRoom = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity addedChessRoom = chessRoomDAO.add(chessRoom);

        ChessRoomEntity foundChessRoom = chessRoomDAO.findById(addedChessRoom.getId());

        assertThat(foundChessRoom).isEqualTo(addedChessRoom);
    }

    @Test
    void delete() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        chessRoomDAO.add(chessRoomEntity);

        chessRoomDAO.delete(chessRoomEntity);

        ChessRoomEntity deletedChessRoom = chessRoomDAO.findById(chessRoomEntity.getId());

        assertThat(deletedChessRoom).isNull();
    }
}