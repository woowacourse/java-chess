package chess.domain.dao;

import chess.domain.chess.dao.DBConnection;
import chess.domain.chess.dao.RoomDAO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class RoomDaoTest {
    private Connection connection = DBConnection.getConnection();

    @Test
    void 삽입() throws SQLException {
        RoomDAO roomDAO = new RoomDAO(connection);
        roomDAO.add();
    }

}
