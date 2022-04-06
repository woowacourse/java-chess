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

    private static final String DATABASE_EMPTY_SYMBOL = "nothing";

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
        final Map<String, PieceDto> allPieces = new HashMap<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            putAll(allPieces, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPieces;
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

    public void updatePiece(final String position, final Piece piece) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (position, team, name) values (?, ?, ?) "
                + "on duplicate key update team = (?), name = (?)";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getName());
            statement.setString(4, piece.getTeam());
            statement.setString(5, piece.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllPieces() {
        final Connection connection = getConnection();
        final String sql = "TRUNCATE piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasGameStateAlready() {
        final Connection connection = getConnection();
        final String sql = "select count(*) as result from game";
        int count = 0;
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    public void saveState(final String state) {
        final Connection connection = getConnection();
        String sql = "insert into game (state) values (?)";
        if (hasGameStateAlready()) {
            sql = "update game set state = (?)";
        }
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTurn(final String turn) {
        final Connection connection = getConnection();
        String sql = "insert into game (turn) values (?)";
        if (hasGameStateAlready()) {
            sql = "update game set turn = (?)";
        }
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, turn);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getGameState() {
        final Connection connection = getConnection();
        final String sql = "select state from game";
        String state = null;
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                state = resultSet.getString("state");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (state == null) {
            return DATABASE_EMPTY_SYMBOL;
        }
        return state;
    }

    public String getTurn() {
        final Connection connection = getConnection();
        final String sql = "select turn from game";
        String turn = null;
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (turn == null) {
            return DATABASE_EMPTY_SYMBOL;
        }
        return turn;
    }

    public void removeGameState() {
        final Connection connection = getConnection();
        final String sql = "TRUNCATE game";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
