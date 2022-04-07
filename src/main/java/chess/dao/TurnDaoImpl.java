package chess.dao;

import chess.util.SqlSelectException;
import chess.util.SqlUpdateException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDaoImpl implements TurnDao {

    private static final int CURRENT_TURN_COLUMN = 1;
    private static final int PREVIOUS_TURN_COLUMN = 2;

    private final DataSource dataSource;

    public TurnDaoImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getCurrentTurn() {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("select * from turn");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(CURRENT_TURN_COLUMN);
            }
            throw new SqlSelectException(SqlSelectException.MESSAGE);
        } catch (SQLException e) {
            throw new SqlSelectException(SqlSelectException.MESSAGE, e);
        } finally {
            dataSource.close();
        }
    }

    @Override
    public void updateTurn(final String currentTurn, final String previousTurn) {
        String sql = "update turn set team = ? where team = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(CURRENT_TURN_COLUMN, currentTurn);
            preparedStatement.setString(PREVIOUS_TURN_COLUMN, previousTurn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlUpdateException(SqlUpdateException.SINGLE_UPDATE_FAILURE_MESSAGE, e);
        } finally {
            dataSource.close();
        }
    }
}
