package chess.dao;

import chess.dao.dto.ChessGame;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.piece.attribute.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlChessDao {
    public void save(ChessGame entity) {
        String query =
                "INSERT INTO CHESSGAME " +
                        "(pieces, running, next_turn) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getPieces());
            preparedStatement.setBoolean(2, entity.isRunning());
            preparedStatement.setString(3, entity.getNextTurn().name());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public Optional<ChessGameManager> findById(Long id) {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE id = ?";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }

            Long rowId = resultSet.getLong("id");
            boolean isRunning = resultSet.getBoolean("running");
            String pieces = resultSet.getString("pieces");
            Color nextTurn = Color.of(resultSet.getString("next_turn"));

            return Optional.of(ChessGameManagerFactory.loadingGame(isRunning, rowId, pieces, nextTurn));
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void update(ChessGame entity) {
        String query =
                "UPDATE CHESSGAME " +
                        "SET pieces = ?, running = ? , nextturn = ?" +
                        "WHERE id = ?";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getPieces());
            preparedStatement.setBoolean(2, entity.isRunning());
            preparedStatement.setString(3, entity.getNextTurn().name());
            preparedStatement.setLong(4, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<ChessGameManager> findAll() {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return getChessGames(preparedStatement);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<ChessGameManager> findAllOnRunning() {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME" +
                        "WHERE running = ?";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, true);
            return getChessGames(preparedStatement);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void deleteAll() {
        String query = "DELETE FROM CHESSGAME";
        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ChessGameManager> getChessGames(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<ChessGameManager> chessGameManagers = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String pieces = resultSet.getString("pieces");
                boolean isRunning = resultSet.getBoolean("running");
                Color nextTurn = Color.of(resultSet.getString("next_turn"));
                chessGameManagers.add(ChessGameManagerFactory.loadingGame(isRunning, id, pieces, nextTurn));
            }
            return chessGameManagers;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}

