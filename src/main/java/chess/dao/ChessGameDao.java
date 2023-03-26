package chess.dao;

import chess.dao.connection.ConnectionDriver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ChessGameDao {

    private final ConnectionDriver connectionDriver;

    public ChessGameDao() {
        this.connectionDriver = new ConnectionDriver();
    }

    public Optional<Integer> findLastGameIdByStatus(String status) {
        String query = "SELECT * FROM game WHERE status = ? ORDER BY game_id DESC LIMIT 1";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, status);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(resultSet.getInt(1));
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다."+ e.getMessage());
        }
    }

    public int add(String status, String turn) {
        String query = "INSERT INTO game (status, turn) VALUES (?, ?)";
        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, status);
            preparedStatement.setObject(2, turn);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
            throw new IllegalArgumentException("INSERT 오류: 입력이 올바르지 않습니다");
        } catch (final SQLException e) {
            throw new RuntimeException("INSERT 오류:" + e.getMessage());
        }
    }

    public void updateStatusByGameId(final String status, final int gameId) {
        String query = "UPDATE game SET status = ? WHERE game_id = ?";
        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, status);
            preparedStatement.setObject(2, gameId);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException("UPDATE 오류:" + e.getMessage());
        }
    }
    

}
