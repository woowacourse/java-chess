package chess.dao;

import chess.dto.MoveDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    DBConnection dbConnection = new DBConnection();
    
    public int save(MoveDto moveDto) {
        final var query = "INSERT INTO movement(source, destination) VALUES(?, ?)";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.sourceCoordinate());
            preparedStatement.setString(2, moveDto.destinationCoordinate());
            return preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int deleteAll() {
        final var query = "DELETE FROM movement";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<MoveDto> selectAllMovement() {
        final var query = "SELECT * FROM movement";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query);
             final var resultSet = preparedStatement.executeQuery()) {
    
            List<MoveDto> moveDtos = new ArrayList<>();
    
            while (resultSet.next()) {
                moveDtos.add(new MoveDto(List.of(
                        resultSet.getString("source"),
                        resultSet.getString("destination")
                )));
            }
            
            return moveDtos;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
