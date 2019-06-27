package chess.domain.dao;

import chess.domain.chess.dao.DBConnection;
import chess.domain.chess.dao.RoomDAO;
import chess.domain.chess.game.Team;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RoomDaoTest {
    private Connection connection = DBConnection.getConnection();

    @Test
    void 삽입() throws SQLException {
        RoomDAO roomDAO = new RoomDAO(connection);
        roomDAO.add();
    }

    @Test
    void 존재하는id들출력() throws SQLException {
        RoomDAO roomDAO = new RoomDAO(connection);

        List<Integer> ids = roomDAO.getIds();
        System.out.println(ids);

    }

    @Test
    void update() throws SQLException {
        RoomDAO roomDAO = new RoomDAO(connection);
        roomDAO.update(2, Team.BLACK);
    }

    @Test
    void select() throws SQLException {
        RoomDAO roomDAO = new RoomDAO(connection);
        Team team = roomDAO.select(2);
        assertThat(team).isEqualTo(Team.BLACK);
    }

    @Test
    void getRecentId() throws SQLException {
        RoomDAO roomDAO = new RoomDAO(connection);

        int id = roomDAO.getRecentId();
        System.out.println(id);
    }

}
