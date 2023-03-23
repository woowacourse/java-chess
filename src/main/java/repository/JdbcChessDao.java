package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MoveHistoryDto;

public class JdbcChessDao implements ChessDao {

    private final JdbcConnector connector;

    public JdbcChessDao(JdbcConnector connector) {
        this.connector = connector;
    }

    @Override
    public void addGame(String gameName) {
        final String query = "INSERT INTO game (gameName) VALUES (?)";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<String> findAllGame() {
        final String query = "SELECT gameName FROM game";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> gameNames = new ArrayList<>();
            while (resultSet.next()) {
                String gameName = resultSet.getString("gameName");
                gameNames.add(gameName);
            }
            return gameNames;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void findBoardByGameName() {}

    public long findGameIdByGameName(String gameName) {
        final String query = "SELECT _id FROM game WHERE gameName = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("_id");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteAllGame() {
        final String query = "DELETE from game";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveBoard() {

    }

    @Override
    public void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto) {
        final String query = "INSERT INTO moveHistory (source, target, pieceOnTarget, g_id) VALUES (?,?,?,?)";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveHistoryDto.getSource());
            preparedStatement.setString(2, moveHistoryDto.getTarget());
            preparedStatement.setString(3, moveHistoryDto.getPiece());
            preparedStatement.setLong(4, game_id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<MoveHistoryDto> findMoveHistoryByGameId(long game_id) {
        final String query = "SELECT source, target, pieceOnTarget FROM moveHistory WHERE g_id = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, game_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MoveHistoryDto> moveHistoryDtos = new ArrayList<>();
            while (resultSet.next()) {
                String source = resultSet.getString("source");
                String target = resultSet.getString("target");
                String piece = resultSet.getString("pieceOnTarget");
                moveHistoryDtos.add(new MoveHistoryDto(source, target, piece));
            }
            return moveHistoryDtos;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
