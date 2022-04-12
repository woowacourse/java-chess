package chess.dao;

import chess.domain.state.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessDao {

    public void saveState(State state) {
        final String sql = "insert into chess_game (state) values (?)";
        try (final PreparedStatement statement = Connector.getConnection().prepareStatement(sql)) {
            statement.setString(1, state.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateState(State state) {
        final String sql = "update chess_game set state = ?";
        try (final PreparedStatement statement = Connector.getConnection().prepareStatement(sql)) {
            statement.setString(1, state.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getState() {
        final String sql = "select state from chess_game";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString("state");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        final String sql = "truncate table chess_game";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
