package chess.dao;

import chess.dao.connection.ConnectionDriver;
import chess.domain.game.StateOfChessGame;
import chess.domain.piece.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGameDao {

    private final ConnectionDriver connectionDriver;

    public ChessGameDao() {
        this.connectionDriver = new ConnectionDriver();
    }

    public void deleteGame() {
        String query = "DELETE FROM game";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("DELETE 오류:" +e.getMessage());
        }
    }

    public Optional<Integer> findGameId() {
        String query = "SELECT * FROM game ORDER BY game_id DESC LIMIT 1";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int gameId = resultSet.getInt("game_id");
                return Optional.of(gameId);
            }
            return Optional.empty();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("SELECT 오류:"+ e.getMessage());
        }
    }

    public Optional<StateOfChessGame> findStatusByGameId(final int gameId) {
        String query = "SELECT status FROM game WHERE game_Id = ?";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                return Optional.of(StateOfChessGame.of(status));
            }
            return Optional.empty();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다.");
        }
    }


    public void updateStatus(final StateOfChessGame status) {
        String query = "UPDATE game SET status = ?";
        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, status.name());
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException("UPDATE 오류:" + e.getMessage());
        }
    }


    public Optional<StateOfChessGame> findGameStatus() {
        String query = "SELECT * FROM game ORDER BY game_id DESC LIMIT 1";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                return Optional.of(StateOfChessGame.of(status));
            }
            return Optional.empty();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("SELECT 오류:"+ e.getMessage());
        }
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
    public Optional<Integer> findLastGameId() {
        String query = "SELECT * FROM game ORDER BY game_id DESC LIMIT 1";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(resultSet.getInt(1));
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다."+ e.getMessage());
        }
    }

    public void save(StateOfChessGame status, Color turn) {
        String query = "INSERT INTO game (game_id, status, turn) VALUES (1, ?, ?)";
        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, status.name());
            preparedStatement.setObject(2, turn.name());
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException("INSERT 오류:" + e.getMessage());
        }
    }

    public void updateTurn(final String turn, final int gameId) {
        String query = "UPDATE game SET turn = ? WHERE game_id = ?";
        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, turn);
            preparedStatement.setObject(2, gameId);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException("UPDATE 오류:" + e.getMessage());
        }
    }

    public StateOfChessGame findGameStatusByGameId(final int gameId) {
        String query = "SELECT status FROM game WHERE game_Id = ?";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                return StateOfChessGame.of(status);
            }
            throw new IllegalArgumentException("게임이 존재하지 않습니다.");
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다.");
        }
    }


    public Map<Integer,StateOfChessGame> findChessRoomIds() {
        String query = "SELECT * FROM game";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            Map<Integer,StateOfChessGame> gameIdList = new LinkedHashMap<>();
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int gameId = resultSet.getInt("game_id");
                String status = resultSet.getString("status");
                gameIdList.put(gameId, StateOfChessGame.of(status));
            }
            return gameIdList;
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다.");
        }
    }

    public void update(final StateOfChessGame status, final Color turn) {
        String query = "UPDATE game SET status = ?, turn = ?";
        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, status.name());
            preparedStatement.setObject(2, turn.name());

            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException("UPDATE 오류:" + e.getMessage());
        }
    }
}
