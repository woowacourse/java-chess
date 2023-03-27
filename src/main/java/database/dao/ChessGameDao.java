package database.dao;

import database.connection.ConnectionGenerator;
import domain.ChessGame;
import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;
import domain.position.Positions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class ChessGameDao {
    public void saveBoard(ChessGame chessGame, String boardName) {
        final long boardId = saveBoardTeamAndReturnBoardId(boardName, chessGame.getTeam());
        final var query = "insert into board_pieces(board_id, position, type, team) values(?, ?, ?, ?)";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Entry<Position, Piece> entry : chessGame.getPieces().entrySet()) {
                preparedStatement.setLong(1, boardId);
                preparedStatement.setString(2, entry.getKey().getName());
                preparedStatement.setString(3, entry.getValue().getClass().getName());
                preparedStatement.setString(4, entry.getValue().getTeam().name());
                preparedStatement.execute();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long saveBoardTeamAndReturnBoardId(String boardName, Team team) {
        final var query = "insert into board(board_name, start_team) value(?, ?);";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, boardName);
            preparedStatement.setString(2, team.name());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            throw new IllegalArgumentException("다른 방번호를 입력해 보세요");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Position, Piece> findPiecesByBoardName(final String boardName) {
        Map<Position, Piece> pieces = new HashMap<>();
        long boardId = findBoardIdByBoardName(boardName);
        final var query = "select type, team, position from board_pieces where board_id = ?";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, boardId);

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pieceType = resultSet.getString("type");
                Team team = Team.valueOf(resultSet.getString("team"));
                Constructor pieceConstructor = Class.forName(pieceType)
                        .getDeclaredConstructor(Team.class);
                Piece piece = (Piece) pieceConstructor.newInstance(team);
                Position position = Positions.from(resultSet.getString("position"));
                pieces.put(position, piece);
            }
            return pieces;
        } catch (final SQLException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException |
                       InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private long findBoardIdByBoardName(final String boardName) {
        final var query = "select id from board where board_name = ?";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, boardName);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            throw new IllegalArgumentException("다른 방 번호를 입력해 보세요.");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team findStartTeamByBoardName(final String boardName) {
        final var query = "select start_team from board where board_name = ?";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, boardName);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Team.valueOf(resultSet.getString("start_team"));
            }
            throw new IllegalArgumentException("다른 방 번호를 입력해 보세요.");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
