package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;
import chess.view.PieceName;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public static Board create() {
        final var query = "INSERT INTO board() VALUES()";

        final int id = JdbcTemplate.insertAndReturnKey(query);

        return new Board(id, createMapById(id));
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
        try (final var connection = DBConnection.get()) {
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
        try (final var connection = DBConnection.get()) {
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

        final var targetQuery = String.format(query, target.getCoordinate());
        JdbcTemplate.executeUpdate(targetQuery, PieceName.findByPiece(sourcePiece), board.getId());

        final var sourceQuery = String.format(query, source.getCoordinate());
        JdbcTemplate.executeUpdate(sourceQuery, PieceName.findByPiece(new Empty(Team.NONE)), board.getId());
    }
}
