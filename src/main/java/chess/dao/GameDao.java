package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.game.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class GameDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new RuntimeException("DB 연결 오류:" + e.getMessage()); // TODO: 2023/03/26 예외 변경
        }
    }

    public Integer save(Game game) {
        String sql = "INSERT INTO game (is_finished, turn) VALUES (?, ?)";
        try (final var connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, game.isFinished());
            preparedStatement.setString(2, game.getTurn().name());

            preparedStatement.execute();
            Integer id = findIdFrom(preparedStatement.getGeneratedKeys());
            save(id, game.getPieces());
            return id;
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 오류:" + e.getMessage());
        }
    }

    private void save(Integer gameId, Map<Position, Piece> pieces) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            save(gameId, entry.getKey(), entry.getValue());
        }
    }

    private void save(Integer gameId, Position position, Piece piece) {
        String sql = "INSERT INTO piece (game_id, file, `rank`, `type`, team) VALUES (?, ?, ?, ?, ?)";
        try (final var connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, position.getFile().name());
            preparedStatement.setString(3, position.getRank().name());
            preparedStatement.setString(4, piece.getType().name());
            preparedStatement.setString(5, piece.getTeam().name());

            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }

    private Integer findIdFrom(ResultSet generatedKeys) {
        try {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }

    public void put(Integer gameId, Game game) {
        String sql = "UPDATE game SET is_finished = ?, turn = ? WHERE id = ?";
        try (final var connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, game.isFinished());
            preparedStatement.setString(2, game.getTurn().name());
            preparedStatement.setInt(3, gameId);

            preparedStatement.executeUpdate();
            put(gameId, game.getPieces());
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 오류:" + e.getMessage());
        }
    }

    private void put(Integer gameId, Map<Position, Piece> pieces) {
        deletePiecesBy(gameId);
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            save(gameId, entry.getKey(), entry.getValue());
        }
    }

    private void deletePiecesBy(Integer gameId) {
        String sql = "DELETE FROM piece WHERE game_id = ?";
        try (final var connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }

    public Game findBy(Integer gameId) {
        String sql = "SELECT turn FROM game WHERE id = ?";
        try (final var connection = connect()) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var turn = Team.valueOf(resultSet.getString("turn"));
                Map<Position, Piece> pieces = findAllPiecesBy(gameId);
                return new Game(turn, new Board(pieces));
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }

    private Map<Position, Piece> findAllPiecesBy(Integer gameId) {
        String sql = "SELECT file, `rank`, `type`, team FROM piece WHERE game_id = ?";
        try (final var connection = connect()) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);

            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                var file = File.valueOf(resultSet.getString("file"));
                var rank = Rank.valueOf(resultSet.getString("rank"));
                var team = Team.valueOf(resultSet.getString("team"));
                var pieceType = PieceType.valueOf(resultSet.getString("type"));
                pieces.put(new Position(file, rank), pieceType.create(team));
            }
            return pieces;
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }

    public void end(Integer gameId) {
        String sql = "UPDATE game SET is_finished = true WHERE id = ?";
        try (final var connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 오류:" + e.getMessage());
        }
    }

    public boolean hasUnfinished() {
        String sql = "SELECT id from game WHERE is_finished = false ORDER BY id";
        try (final var connection = connect()) {
            final var preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }

    public Integer findIdOfLastUnfinished() {
        String sql = "SELECT id from game WHERE is_finished = false ORDER BY id";
        try (final var connection = connect()) {
            final var preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 오류:" + exception.getMessage());
        }
    }
}
