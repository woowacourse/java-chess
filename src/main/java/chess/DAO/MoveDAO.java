package chess.DAO;

import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoveDAO {

    private final Connection connection;

    public MoveDAO() {
        this.connection = MySQLConnectionSetting.getInstance().getConnection();
    }

    public void addMove(final Position from, final Position to) throws SQLException {
        String query = "insert into move (start, end) values (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, from.toString());
            statement.setString(2, to.toString());
            statement.executeUpdate();
        }
    }

    public Map<Position, Position> getMoves() throws SQLException {
        String query = "select * from move";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            Map<Position, Position> moves = new LinkedHashMap<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String from = resultSet.getString("start");
                String to = resultSet.getString("end");
                moves.put(Position.of(from), Position.of(to));
            }
            return moves;
        }
    }

    public void reset() throws SQLException {
        String query = "delete from move";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
}
