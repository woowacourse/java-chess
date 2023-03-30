package chess.repository;

import chess.domain.chessGame.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.utils.BoardToString;
import chess.utils.StringToBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DbChessGameDao implements ChessGameDao {

    private static final String DELIMITER = ",";
    private static final String TABLE_NAME = "chess_game";
    private static final String USER_COLUMN = "user";
    private static final String BOARD_ROWS_COLUMN = "board_Row8to1";
    private static final String TURN_COLUMN = "turn";
    private static final String INSERT_QUERY = "INSERT " + TABLE_NAME +
            " (" + USER_COLUMN + ", " + BOARD_ROWS_COLUMN + ", " + TURN_COLUMN + ") VALUES (?, ?, ?)";
    private static final String FIND_QUERY = "SELECT " + USER_COLUMN + " FROM " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT " + BOARD_ROWS_COLUMN + ", " + TURN_COLUMN +
            " FROM " + TABLE_NAME + " WHERE user = ";
    private static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME + " WHERE user = ";

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public List<String> getUsers() {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(FIND_QUERY)) {
            final var resultSet = preparedStatement.executeQuery();
            return collectUsers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> collectUsers(ResultSet resultSet) throws SQLException {
        List<String> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(resultSet.getString(USER_COLUMN));
        }
        return users;
    }

    @Override
    public boolean find(String user) {
        return getUsers().contains(user);
    }

    @Override
    public void insert(String user, ChessBoard chessBoard) {
        List<String> convertToString = BoardToString.convert(chessBoard.getChessBoard());
        String board_Row8to1 = String.join(DELIMITER, convertToString);
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, board_Row8to1);
            preparedStatement.setString(3, chessBoard.getTurn());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessBoard select(String user) {
        if (!find(user)) {
            return null;
        }
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(SELECT_QUERY + "'" + user + "'")) {
            final var resultSet = preparedStatement.executeQuery();
            return getChessBoard(resultSet);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ChessBoard getChessBoard(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String board_Row8to1 = resultSet.getString(BOARD_ROWS_COLUMN);
            List<String> chessBoardText = List.of(board_Row8to1.split(DELIMITER));

            Map<Position, Piece> chessBoard = StringToBoard.convert(chessBoardText);
            Color turn = Color.valueOf(resultSet.getString(TURN_COLUMN));
            return new ChessBoard(chessBoard, turn);
        }
        return null;
    }

    @Override
    public void update(String user, ChessBoard chessBoard) {
        delete(user, chessBoard);
        insert(user, chessBoard);
    }

    @Override
    public void delete(String user, ChessBoard chessBoard) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(DELETE_QUERY + "'" + user + "'")) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
