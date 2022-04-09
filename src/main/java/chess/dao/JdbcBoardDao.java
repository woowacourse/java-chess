package chess.dao;

import static chess.dao.JdbcGameStatusDao.*;

import chess.util.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JdbcBoardDao implements BoardDao {

    private final Connection connection = JdbcConnector.getConnection();

    @Override
    public void update(String position, String piece) {
        String sql = "update board set piece = ? where position = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(PARAMETER_FIRST_INDEX, piece);
            preparedStatement.setString(PARAMETER_SECOND_INDEX, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getBoard() {
        final String sql = "select * from board";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();

            Map<String, String> board = new HashMap<>();
            while (resultSet.next()) {
                board.put(resultSet.getString("position"), resultSet.getString("piece"));
            }

            return board;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reset(final Map<String, String> board) {
        removeAll();
        final String sql = "insert into board (position, piece) values (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Entry<String, String> boardEntry : board.entrySet()) {
                preparedStatement.setString(PARAMETER_FIRST_INDEX, boardEntry.getKey());
                preparedStatement.setString(PARAMETER_SECOND_INDEX, boardEntry.getValue());
                preparedStatement.executeUpdate();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll() {
        String sql = "truncate table board";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
