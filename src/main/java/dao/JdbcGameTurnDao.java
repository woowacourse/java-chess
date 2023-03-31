package dao;

import dto.GameTurnDto;
import util.DBUtil;

import java.sql.SQLException;

public class JdbcGameTurnDao implements GameTurnDao{

    @Override
    public void save(GameTurnDto gameTurnDto) {
        final var query = "INSERT INTO game_turn(turn) VALUES (?)";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameTurnDto.getTurnOfColor());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameTurnDto find() {
        String turnOfColor = "WHITE";
        final var query = "SELECT turn FROM game_turn";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                turnOfColor = resultSet.getString("turn");
            }
            return new GameTurnDto(turnOfColor);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameTurnDto gameTurnDto) {
        delete();
        save(gameTurnDto);
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM game_turn";
        try (final var connection = DBUtil.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
