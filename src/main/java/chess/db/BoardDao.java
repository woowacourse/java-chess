package chess.db;

import chess.domain.Board;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class BoardDao {
    private final ConnectionGenerator connectionGenerator;

    BoardDao() {
        this(new ProductionConnectionGenerator());
    }

    BoardDao(ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public Board loadAll() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM board");
            final ResultSet resultSet = statement.executeQuery();

            final Map<Position, Piece> loadedBoard = new HashMap<>();
            while (resultSet.next()) {
                int positionRow = resultSet.getInt("position_row");
                int positionColumn = resultSet.getInt("position_column");
                Team team = Team.valueOf(resultSet.getString("team"));
                Kind kind = Kind.valueOf(resultSet.getString("kind"));
                boolean isMoved = resultSet.getBoolean("is_moved");
                Position position = Position.of(positionRow, positionColumn);
                Piece piece = kind.createPiece(team, isMoved);

                loadedBoard.put(position, piece);
            }

            return new Board(loadedBoard);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Movement movement, Piece piece) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE board SET position_row = ?, position_column = ?, is_moved = ? WHERE id = ?");

            statement.setInt(1, movement.target().row());
            statement.setInt(2, movement.target().column());
            statement.setBoolean(3, piece.isMoved());
            statement.setInt(4, findIdByPosition(movement.source()));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int findIdByPosition(Position position) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM board WHERE position_row = ? AND position_column = ?");
            statement.setInt(1, position.row());
            statement.setInt(2, position.column());
            final var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void addAll(Board board) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            for (Entry<Position, Piece> piece : board.getPieces().entrySet()) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO board(position_row, position_column, team, kind, is_moved)"
                                + " VALUES (?, ?, ?, ?, ?)");

                statement.setInt(1, piece.getKey().row());
                statement.setInt(2, piece.getKey().column());
                statement.setString(3, piece.getValue().team().name());
                statement.setString(4, piece.getValue().kind().name());
                statement.setBoolean(5, piece.getValue().isMoved());

                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM board");

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
