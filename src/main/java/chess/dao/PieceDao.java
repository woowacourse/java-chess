package chess.dao;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void saveAll(final Map<Position, Piece> board) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (position, team, name) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            saveAll(board, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveAll(final Map<Position, Piece> board, final PreparedStatement statement) throws SQLException {
        for (Entry<Position, Piece> entry : board.entrySet()) {
            statement.setString(1, entry.getKey().toString());
            statement.setString(2, entry.getValue().getTeam());
            statement.setString(3, entry.getValue().getName());
            statement.executeUpdate();
        }
    }

    public Map<String, PieceDto> findAll() {
        final Connection connection = getConnection();
        final String sql = "select position, team, name from piece";
        final Map<String, PieceDto> all = new HashMap<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            putAll(all, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    private void putAll(final Map<String, PieceDto> all, final ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            all.put(resultSet.getString("position"), new PieceDto(
                            resultSet.getString("team"),
                            resultSet.getString("name")
                    )
            );
        }
    }

    public void save(final String position, final Piece piece) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (position, team, name) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeByPosition(final String position) {
        final Connection connection = getConnection();
        final String sql = "delete from piece where position = (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll() {
        final Connection connection = getConnection();
        final String sql = "TRUNCATE piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
