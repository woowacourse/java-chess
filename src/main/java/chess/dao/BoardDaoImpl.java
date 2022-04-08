package chess.dao;

import chess.util.SqlSelectException;
import chess.util.SqlUpdateException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDaoImpl implements BoardDao {

    private static final int PIECE_COLUMN = 1;
    private static final int POSITION_COLUMN = 2;

    private final DataSource dataSource;

    public BoardDaoImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getBoard() {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("select * from board");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            Map<String, String> board = new HashMap<>();
            while (resultSet.next()) {
                board.put(resultSet.getString("position"), resultSet.getString("piece"));
            }
            return board;
        } catch (SQLException e) {
            throw new SqlSelectException(SqlSelectException.MESSAGE, e);
        } finally {
            dataSource.close();
        }
    }

    @Override
    public void updatePosition(final String position, final String piece) {
        String sql = "update board set piece = ? where position = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(PIECE_COLUMN, piece);
            preparedStatement.setString(POSITION_COLUMN, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlUpdateException(SqlUpdateException.SINGLE_UPDATE_FAILURE_MESSAGE, e);
        } finally {
            dataSource.close();
        }
    }

    @Override
    public void updateBatchPositions(final Map<String, String> board) {
        String sql = "update board set piece = ? where position = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql)) {

            for (Entry<String, String> boardEntry : board.entrySet()) {
                preparedStatement.setString(PIECE_COLUMN, boardEntry.getValue());
                preparedStatement.setString(POSITION_COLUMN, boardEntry.getKey());
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new SqlUpdateException(SqlUpdateException.MULTIPLE_UPDATE_FAILURE_MESSAGE, e);
        } finally {
            dataSource.close();
        }
    }
}
