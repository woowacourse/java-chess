package database;

import chess.domain.piece.Team;
import chess.game.Turn;
import database.dto.ChessGameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessGameDao {

    public int save(ChessGameDto chessGameDto) {
        String query = "INSERT INTO chess_game VALUES (?, ?, ?)";
        int id = generateId();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, chessGameDto.getGameStatus());
            preparedStatement.setString(3, chessGameDto.getTurn());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void update(ChessGameDto chessGameDto) {
        String query = "UPDATE chess_game SET game_status = ?, turn = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGameDto.getGameStatus());
            preparedStatement.setString(2, chessGameDto.getTurn());
            preparedStatement.setInt(3, chessGameDto.getGameId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM chess_game WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Turn findTurnById(int game_id) {
        String query = "SELECT turn FROM chess_game WHERE id = ?";
        String turn = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, game_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
            }
            return new Turn(Team.valueOf(turn));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findContinuingGameIds() {
        String query = "SELECT id FROM chess_game WHERE game_status = ?";

        List<Integer> continuingGameIds = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "CONTINUING");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                continuingGameIds.add(id);
            }
            return continuingGameIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int generateId() {
        List<Integer> gameIds = findAllGameId();
        if (gameIds.isEmpty()) {
            return 1;
        }
        return Collections.max(gameIds) + 1;
    }

    private List<Integer> findAllGameId() {
        String query = "SELECT id FROM chess_game";

        List<Integer> allGameIds = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                allGameIds.add(id);
            }
            return allGameIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
