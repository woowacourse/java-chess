package chess.domain.chess.dao;

import chess.domain.chess.game.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public void add() throws SQLException {
        String query = "INSERT INTO room (team) VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Team.WHITE.name());
        pstmt.executeUpdate();
    }

    public void update(Team team) {

    }

    public Team select(int roomId) {

        return Team.BLACK;
    }

    public List<Integer> getIds() {

        return new ArrayList<>();
    }

}
