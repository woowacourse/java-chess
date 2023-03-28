package chess.dao;

import chess.domain.ChessGame;
import chess.dto.GameInfoDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcGameDao implements GameDao {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "username"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Integer> findAllPossibleId() {
        final var query = "SELECT game_id FROM game WHERE game_status != ? AND game_status != ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "END");
            preparedStatement.setString(2, "CATCH");

            final var resultSet = preparedStatement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(Integer.parseInt(resultSet.getString("game_id")));
            }
            return ids;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findAllImpossibleId() {
        final var query = "SELECT game_id FROM game WHERE game_status = ? OR game_status = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "END");
            preparedStatement.setString(2, "CATCH");

            final var resultSet = preparedStatement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(Integer.parseInt(resultSet.getString("game_id")));
            }
            return ids;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameInfoDto findById(int gameId) {
        final var gameQuery = "SELECT * FROM game WHERE game_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return GameInfoDto.create(resultSet.getString("game_status"), resultSet.getString("game_turn"));
            } else {
                return null;
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(int gameId, ChessGame chessGame) {
        final var gameQuery = "INSERT INTO game VALUES(?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, chessGame.getStatus().name());
            preparedStatement.setString(3, chessGame.getTurn().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateById(int gameId, ChessGame chessGame) {
        deleteById(gameId);
        save(gameId, chessGame);
    }

    @Override
    public void deleteById(int gameId) {
        final var gameQuery = "DELETE FROM game WHERE game_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        final var gameQuery = "DELETE FROM game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
