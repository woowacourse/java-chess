package dao;

import chess.ChessGame;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChessStatusDao {
    private final DBConnection dbConnection = new DBConnection();

    public List<String> gameIds() {
        final var query = "SELECT game_id FROM chess_status;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            List<String> gameIds = new ArrayList<>();
            while (resultSet.next()) {
                final String gameId = resultSet.getString("game_id");
                gameIds.add(gameId);
            }

            return gameIds;
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임 상태를 저장하는데 실패했습니다.");
        }
    }

    public Color selectTurn(final String gameId) {
        final var query = "SELECT * FROM chess_status WHERE game_id=?;";
        try (final Connection connection = dbConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(gameId));

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String turnString = resultSet.getString("turn");
                System.out.println(turnString);
                return Color.valueOf(turnString);
            }

            return null;
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("체스 게임의 상태를 불러오는데 실패했습니다.");
        }
    }

    public void saveChessStatus(final ChessGame chessGame, final String gameId) {
        final var query = "SELECT * FROM chess_status WHERE game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(gameId));

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                createChessStatus(chessGame);
                // TODO: Service 에서 해줄 수 있을 것 같다! -ㅎㅂ
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("체스 게임 상태를 저장하는데 실패했습니다.");
        }
    }

    public String createChessStatus(final ChessGame chessGame) {
        final var query = "INSERT INTO chess_status (turn) VALUE (?);";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, chessGame.turn().name());
            preparedStatement.executeUpdate();

            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getString(1);
            }
            return null;
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("체스 게임 상태를 생성하는데 실패했습니다.");
        }
    }

    public void update(final ChessGame chessGame, final String gameId) {
        final var query = "UPDATE chess_status SET turn=? WHERE game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGame.turn().name());
            preparedStatement.setString(2, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("게임을 업데이트하는데 실패했습니다.");
        }
    }
}
