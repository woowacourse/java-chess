package chess.repository;

import chess.domain.chessGame.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.utils.BoardToString;
import chess.utils.StringToBoard;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public final class DbChessGameDao implements ChessGameDao {

    private static final String DELIMITER = ",";
    private static final String TABLE_NAME = "chess_game";
    private static final String BOARD_ROWS_COLUMN = "board_Row8to1";
    private static final String TURN_COLUMN = "turn";
    private static final String INSERT_QUERY = "INSERT " + TABLE_NAME +
            " (" + BOARD_ROWS_COLUMN + ", " + TURN_COLUMN + ") VALUES (?, ?)";
    private static final String SELECT_QUERY = "SELECT " + BOARD_ROWS_COLUMN + ", " + TURN_COLUMN +
            " FROM " + TABLE_NAME;
    private static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME;

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void insert(ChessBoard chessBoard) {
        List<String> convertToString = BoardToString.convert(chessBoard.getChessBoard());
        String board_Row8to1 = String.join(DELIMITER, convertToString);
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, board_Row8to1);
            preparedStatement.setString(2, chessBoard.getTurn());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessBoard select() {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String board_Row8to1 = resultSet.getString(BOARD_ROWS_COLUMN);
                List<String> chessBoardText = List.of(board_Row8to1.split(DELIMITER));

                Map<Position, Piece> chessBoard = StringToBoard.convert(chessBoardText);
                Color turn = Color.valueOf(resultSet.getString(TURN_COLUMN));
                return new ChessBoard(chessBoard, turn);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(ChessBoard chessBoard) {
        delete(chessBoard);
        insert(chessBoard);
    }

    @Override
    public void delete(ChessBoard chessBoard) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
