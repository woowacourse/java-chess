package chess.dao;

import chess.dao.util.DatabaseConnector;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private static final String INVALID_GAME_ID_EXCEPTION_MESSAGE = "유효하지 않은 게임 방 번호입니다.";

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void saveById(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "insert into game (id, turn) values (?, ?)";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            statement.setString(2, Color.BLACK.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

    public boolean findById(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select id from game where id = ?";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
        return true;
    }

    public boolean findForceEndFlagById(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select force_end_flag from game where id = ?";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
            }
            return resultSet.getBoolean("force_end_flag");
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

    public Color findTurnById(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select id, turn from game where id = ?";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
            }
            return Color.of(resultSet.getString("turn"));
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

    public void updateTurnById(Color nextTurn, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "update game set turn = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nextTurn.getName());
            statement.setString(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

    public void updateForceEndFlagById(boolean forceEndFlag, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "update game set force_end_flag = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, forceEndFlag);
            statement.setString(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

    public void deleteById(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

}
