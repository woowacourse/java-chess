package chess.dao;

import chess.domain.Board;
import chess.domain.Rank;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ChessDAO {

    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public int saveGame(Board board, String team) {
        String sql = "insert into Game(turn) values (?)";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team);
            statement.executeUpdate();

            int gameId = getLastInsertKey(connection);
            savePieces(board.getBoard(), gameId);

            return gameId;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    private int getLastInsertKey(Connection connection) {
        String sql = "select last_insert_id() from Game";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet lastInsertKey = statement.executeQuery();
            if (lastInsertKey.next()) {
                return lastInsertKey.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        throw new RuntimeException("데이터를 찾지 못했습니다.");
    }

    private void savePieces(Map<Row, Rank> board, int gameId) {
        for (Row row : Row.values()) {
            Rank rank = board.get(row);
            for (Column col : Column.values()) {
                Piece piece = rank.getPiece(col);
                savePiece(piece.getName(), String.valueOf(col.getValue()), row.getValue(), piece.getTeamName(),
                        gameId);
            }
        }
    }

    private void savePiece(String type, String col, int row, String team, int gameId) {
        String sql = "insert into Piece"
                + "(piece_type, position_column, position_row, team, game_id) values (?,?,?,?,?)";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);
            statement.setString(2, col);
            statement.setInt(3, row);
            statement.setString(4, team);
            statement.setInt(5, gameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    public Board findBoardByGameId(int gameId) {
        try (Connection connection = databaseConnector.getConnection()) {
            Map<Row, Rank> ranks = new HashMap<>();
            for (Row row : Row.values()) {
                ranks.put(row, findRankByRow(row.getValue(), gameId, connection));
            }
            return new Board(ranks);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    private Rank findRankByRow(int row, int gameId, Connection connection) {
        String sql = "select piece_type, position_column, position_row, team"
                + " from Piece where position_row = ? and game_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, row);
            statement.setInt(2, gameId);
            ResultSet resultSet = statement.executeQuery();

            EnumMap<Column, Piece> rank = new EnumMap<>(Column.class);
            while (resultSet.next()) {
                rank.put(Column.find(resultSet.getString("position_column")),
                        PieceFactory.of(
                                resultSet.getString("piece_type"),
                                resultSet.getString("position_column"),
                                resultSet.getInt("position_row"),
                                resultSet.getString("team")));
            }
            return new Rank(rank);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    public Piece findPieceByPosition(Position position, int gameId) {
        String sql = "select piece_type, position_column, position_row, team"
                + " from Piece where position_column = ? and position_row = ? and game_id = ?";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(position.getCol().getValue()));
            statement.setInt(2, position.getRow().getValue());
            statement.setInt(3, gameId);
            ResultSet resultSet = statement.executeQuery();

            Piece piece = null;
            while (resultSet.next()) {
                piece = PieceFactory.of(
                        resultSet.getString("piece_type"),
                        resultSet.getString("position_column"),
                        resultSet.getInt("position_row"),
                        resultSet.getString("team"));
            }
            return piece;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    public void updatePiece(Piece piece, int gameId) {
        String sql = "update Piece set piece_type = ?, team = ?"
                + " where position_column = ? and position_row = ? and game_id = ?";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, piece.getName());
            statement.setString(2, piece.getTeamName());
            statement.setString(3, String.valueOf(piece.getColValue()));
            statement.setInt(4, piece.getRowValue());
            statement.setInt(5, gameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    public Team findTurnByGameId(int gameId) {
        String sql = "select turn from Game where game_id = ?";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Team.valueOf(resultSet.getString("turn"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        throw new RuntimeException("데이터를 찾지 못했습니다.");
    }

    public void updateTurn(Team team, int gameId) {
        String sql = "update Game set turn = ? where game_id = ?";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.name());
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}
