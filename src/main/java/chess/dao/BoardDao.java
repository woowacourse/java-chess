package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.PieceName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Board create() {
        final var query = "INSERT INTO board() VALUES ()";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeQuery();

            final var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                final var id = generatedKeys.getInt(1);
                return new Board(
                        id,
                        createMapById(id)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Map<Position, Piece> createMapById(final int boardId) {
        final Map<Position, Piece> board = new HashMap<>();

        final var query = "SELECT * FROM board WHERE id = ?";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardId);

            final var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                for (final File file : File.values()) {
                    for (final Rank rank : Rank.values()) {
                        final String position = file.name() + rank.name();
                        board.put(Position.of(file, rank), findByPosition(boardId, position));
                    }
                }
                return board;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static Piece findByPosition(final int boardId, final String position) {
        final var query = "SELECT ? FROM board WHERE boardId = ?";
        try(final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, position);
            preparedStatement.setInt(2, boardId);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return PieceName.findByName(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static Board findById(final int boardId) {
        final var query = "SELECT id FROM board WHERE board_id = ?";
        try(final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardId);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int id = resultSet.getInt(1);

                return new Board(
                        id,
                        createMapById(id)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
