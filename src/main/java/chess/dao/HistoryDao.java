package chess.dao;

import chess.dto.MoveDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao implements ChessDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveHistory(final MoveDto moveDto) {
        final String historySaveQuery = "INSERT INTO history(source, target) VALUES (?,?)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(historySaveQuery)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllHistory() {
        String deleteBoardQuery = "TRUNCATE TABLE history";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(deleteBoardQuery)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> selectAllHistory() {
        String selectAllQuery = "SELECT source, target FROM history";
        List<MoveDto> moveDtos = new ArrayList<>();

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(selectAllQuery)) {
            ResultSet historyResult = preparedStatement.executeQuery();
            while (historyResult.next()) {
                moveDtos.add(makeHistory(historyResult));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moveDtos;
    }

    private MoveDto makeHistory(final ResultSet historyResultSet) throws SQLException {
        String source = historyResultSet.getString("source");
        String target = historyResultSet.getString("target");
        return new MoveDto(source, target);
    }
}

