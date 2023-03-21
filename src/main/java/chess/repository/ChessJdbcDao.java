package chess.repository;

import chess.dto.MoveDto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessJdbcDao implements ChessDao {

    private final ConnectionGenerator connectionGenerator;

    public ChessJdbcDao(final ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    @Override
    public void save(final MoveDto moveDto) {
        final String query = "INSERT INTO move (source, target) VALUES (?, ?)";
        try (final Connection connection = connectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> findAll() {
        final String query = "SELECT * FROM move";
        try (final Connection connection = connectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final List<MoveDto> result = new ArrayList<>();
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String source = resultSet.getString("source");
                final String target = resultSet.getString("target");
                result.add(new MoveDto(source, target));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final String query = "DELETE FROM move";
        try (final Connection connection = connectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
