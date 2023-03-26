package chess.dao;

import chess.domain.piece.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    private GameDao() {
    }

    public static boolean
    enrollGameOf(String userId, String gameId, String turn, String gameName) {
        if (alreadyExistGame(gameName)) {
            return false;
        }
        final var query = "INSERT INTO game VALUES(?, ?, ?, ?)";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, gameId);
            preparedStatement.setString(3, gameName);
            preparedStatement.setString(4, turn);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean alreadyExistGame(String gameName) {
        final var query = "SELECT * FROM game WHERE game_Name = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGameIdOf(String roomName) {
        final var query = "SELECT game_id FROM game WHERE game_name = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roomName);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("game_id");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteGameOf(String gameName) {
        final var query = "DELETE FROM game WHERE game_name = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);

            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Color getGameTurnOf(String gameId) {
        final var query = "SELECT turn FROM game WHERE game_id = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                return Color.valueOf(turn);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getGameNameOf(String currentLoginId) {
        final var query = "SELECT game_name FROM game WHERE user_id = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currentLoginId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSet.getString("game_name"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateTurn(String gameId, String turn) {
        final var query = "UPDATE game set turn = ? WHERE user_id = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setString(2, gameId);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean haveRoomOf(String roomName, String userId) {
        final var query = "SELECT game_id FROM game WHERE game_name = ? and user_id = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roomName);
            preparedStatement.setString(2, userId);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
