package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;
import chess.view.PieceName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        final var query = "INSERT INTO board() VALUES()";

        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.executeUpdate();

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

        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                final String position = file.value() + String.valueOf(rank.value());
                board.put(Position.of(file, rank), findByPosition(boardId, position));
            }
        }
        return board;
    }

    private static Piece findByPosition(final int boardId, final String position) {
        final var query = "SELECT " + position + " FROM board WHERE id = ?";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardId);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String name = resultSet.getString(1);
                return PieceName.findByName(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static Board findById(final int boardId) {
        final var query = "SELECT id FROM board WHERE id = ?";
        try (final var connection = getConnection()) {
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

    public static void updateByMove(final Board board,
                                    final Position source,
                                    final Position target,
                                    final Piece sourcePiece) {
        final var query = "UPDATE board SET %s = ? WHERE id = ?";

        try (final var connection = getConnection()) {
            final var targetQuery = String.format(query, target.getCoordinate());
            final var targetPreparedStatement = connection.prepareStatement(targetQuery);
            targetPreparedStatement.setString(1, PieceName.findByPiece(sourcePiece));
            targetPreparedStatement.setInt(2, board.getId());
            targetPreparedStatement.executeUpdate();

            final var sourceQuery = String.format(query, source.getCoordinate());
            final var sourcePreparedStatement = connection.prepareStatement(sourceQuery);
            sourcePreparedStatement.setString(1, PieceName.findByPiece(new Empty(Team.NONE)));
            sourcePreparedStatement.setInt(2, board.getId());
            sourcePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
