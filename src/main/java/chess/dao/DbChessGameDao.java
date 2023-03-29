package chess.dao;

import chess.domain.chessGame.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.utils.BoardToString;
import chess.utils.StringToBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void insert(ChessBoard chessBoard) {
        final var query = "INSERT  chess_game (board_Row8to1, turn) VALUES (?, ?)";
        List<String> convertToString = BoardToString.convert(chessBoard.getChessBoard());
        String board_Row8to1 = convertToString.stream()
                .collect(Collectors.joining(","));
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, board_Row8to1);
            preparedStatement.setString(2, chessBoard.getTurn());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessBoard select() {
        final var query = "SELECT board_Row8to1, turn FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String board_Row8to1 = resultSet.getString("board_Row8to1");
                List<String> chessBoardText = List.of(board_Row8to1.split(","));

                Map<Position, Piece> chessBoard = StringToBoard.convert(chessBoardText);
                Color turn = Color.valueOf(resultSet.getString("turn"));
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
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
