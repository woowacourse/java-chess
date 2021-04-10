package chess.dao;

import chess.dao.dto.ChessGame;
import chess.domain.piece.attribute.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlChessDao implements ChessDao {
    @Override
    public long save(ChessGame entity) {
        String query =
                "INSERT INTO CHESSGAME " +
                        "(pieces, running, next_turn) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getPieces());
            preparedStatement.setBoolean(2, entity.isRunning());
            preparedStatement.setString(3, entity.getNextTurn().name());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
        throw new IllegalStateException("생성된 ID가 없습니다.");
    }

    @Override
    public Optional<ChessGame> findById(long id) {
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

            return Optional.of(new ChessGame(rowId, nextTurn, isRunning, pieces));
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void update(ChessGame entity) {
        String query =
                "UPDATE CHESSGAME " +
                        "SET pieces = ?, running = ? , next_turn = ?" +
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

    @Override
    public List<ChessGame> findAllOnRunning() {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE running = ?";

        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, true);
            return getChessGames(preparedStatement);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM CHESSGAME " +
                "WHERE id = ?";
        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ChessGame> getChessGames(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<ChessGame> chessGameManagers = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String pieces = resultSet.getString("pieces");
                boolean isRunning = resultSet.getBoolean("running");
                Color nextTurn = Color.of(resultSet.getString("next_turn"));
                chessGameManagers.add(new ChessGame(id, nextTurn, isRunning, pieces));
            }
            return chessGameManagers;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}

