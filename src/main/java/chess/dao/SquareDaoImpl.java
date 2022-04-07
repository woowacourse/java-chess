package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SquareDaoImpl implements SquareDao {

    private final Connection connection;

    public SquareDaoImpl() {
        this.connection = ConnectionManager.getConnection();
    }

    public void save(Position position, Piece piece) {
        final String sql = "insert into square (position, team, symbol) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getKey());
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getSymbol());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> find() {
        final String sql = "select position, team, symbol from square";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            final Map<String, String> squares = createFrom(resultSet);
            return squares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("[ERROR] 저장된 데이터를 가져 올 수 없습니다.");
    }

    private Map<String, String> createFrom(ResultSet resultSet)
            throws SQLException {
        Map<String, String> squares = new HashMap<>();
        while (resultSet.next()) {
            squares.put(resultSet.getString("position"),
                    resultSet.getString("team") + "_" + resultSet.getString("symbol"));
        }
        return squares;
    }

    public void delete() {
        final String sql = "delete from square";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String position, Piece piece) {
        final String sql = "update square set team = ?, symbol = ? where position = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getTeam());
            statement.setString(2, piece.getSymbol());
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
