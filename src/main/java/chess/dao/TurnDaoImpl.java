package chess.dao;

import chess.domain.player.Team;
import chess.dto.TurnDto;
import chess.utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDaoImpl implements TurnDao {

    private final Connection connection;

    public TurnDaoImpl() {
        connection = DbConnector.getConnection();
    }

    @Override
    public TurnDto findTurn() {
        final String sql = "select * from turn";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            return getTurnDto(resultSet);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TurnDto getTurnDto(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        return new TurnDto(resultSet.getString("team"));
    }

    @Override
    public void updateTurn(final String turn) {
        final String sql = "update turn set team = ? where team = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            changeTurn(turn, statement);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeTurn(String turn, PreparedStatement statement) throws SQLException {
        if (turn.equals(Team.WHITE.getName())) {
            statement.setString(1, Team.BLACK.getName());
            statement.setString(2, Team.WHITE.getName());
            statement.executeUpdate();
            return;
        }
        statement.setString(1, Team.WHITE.getName());
        statement.setString(2, Team.BLACK.getName());
        statement.executeUpdate();
    }

    @Override
    public void resetTurn() {
        final String sql = "update turn set team = 'WHITE'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
