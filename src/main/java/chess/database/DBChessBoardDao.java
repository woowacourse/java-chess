package chess.database;

import chess.dto.MoveDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class DBChessBoardDao implements ChessGameDao {

    private final Database database = new Database();

    @Override
    public void saveNotation(final String fromPosition, final String toPosition) {
        var query = "INSERT INTO move_position(fromPosition,toPosition) VALUES(?,?)";
        try (final var connection = database.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fromPosition);
            preparedStatement.setString(2, toPosition);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> selectNotation() {
        List<MoveDto> positions = new ArrayList<>();

        var query = "SELECT * FROM move_position ORDER BY seq ASC";
        try (var connection = database.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fromPosition = resultSet.getString("fromPosition");
                String toPosition = resultSet.getString("toPosition");

                positions.add(MoveDto.of(fromPosition, toPosition));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (positions.isEmpty()) {
            return Collections.emptyList();
        }
        return positions;
    }

    @Override
    public void delete() {
        var query = "TRUNCATE move_position";
        try (var connection = database.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
