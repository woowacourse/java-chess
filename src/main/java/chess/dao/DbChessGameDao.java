package chess.dao;

import chess.domain.chessGame.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.utils.BoardToString;
import chess.utils.StringToBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DbChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(ChessBoard chessBoard) {
        final var query = "INSERT  chess_game (board_row8, board_row7, board_row6, board_row5, board_row4, board_row3, board_row2, board_row1) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<String> convertToString = BoardToString.convert(chessBoard.getChessBoard());
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= convertToString.size(); i++) {
                preparedStatement.setString(i, convertToString.get(i - 1));
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessBoard select() {
        final var query = "SELECT board_row8, board_row7, board_row6, board_row5, board_row4, board_row3, board_row2, board_row1 FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                List<String> chessBoardText = new ArrayList<>();
                chessBoardText.add(resultSet.getString("board_row8"));
                chessBoardText.add(resultSet.getString("board_row7"));
                chessBoardText.add(resultSet.getString("board_row6"));
                chessBoardText.add(resultSet.getString("board_row5"));
                chessBoardText.add(resultSet.getString("board_row4"));
                chessBoardText.add(resultSet.getString("board_row3"));
                chessBoardText.add(resultSet.getString("board_row2"));
                chessBoardText.add(resultSet.getString("board_row1"));
                Map<Position, Piece> chessBoard = StringToBoard.convert(chessBoardText);
                return new ChessBoard(chessBoard);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(ChessBoard chessBoard) {
        delete(chessBoard);
        save(chessBoard);
    }

    @Override
    public void delete(ChessBoard chessBoard) {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
