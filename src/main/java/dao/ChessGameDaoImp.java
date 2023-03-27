package dao;

import domain.board.Board;
import domain.board.ChessGame;
import domain.board.Turn;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Coordinate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDaoImp implements ChessGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION,
                    MYSQL_USERNAME,
                    MYSQL_PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void insert(final ChessGame chessGame) {
        final var query = "INSERT INTO chess_game(piece_row, piece_col, piece, piece_color, turn) VALUES (?, ?, ?, ?, ?)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            Board board = chessGame.getBoard();
            Map<Coordinate, Piece> locations = board.getPieceLocations();

            locations.forEach((k, v) -> {
                try {
                    preparedStatement.setString(1, String.valueOf(k.getRow()));
                    preparedStatement.setString(2, String.valueOf(k.getCol()));
                    preparedStatement.setString(3, PieceConverter.parse(v));
                    preparedStatement.setString(4, v.getColor().name());
                    preparedStatement.setString(5, chessGame.getTurn().color().name());
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (final SQLException e) {
            System.err.println("생성 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public ChessGame read() {
        final var query = "SELECT * FROM chess_game";
        Map<Coordinate, Piece> locations = new HashMap<>();

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            Turn turn = new Turn(Color.WHITE);

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Coordinate location = new Coordinate(
                        Integer.parseInt(resultSet.getString("piece_row")),
                        Integer.parseInt(resultSet.getString("piece_col"))
                );
                Piece piece = PieceConverter.combine(
                        resultSet.getString("piece"),
                        Color.valueOf(resultSet.getString("piece_color"))
                );
                locations.put(location, piece);
                turn = new Turn(Color.valueOf(resultSet.getString("turn")));
            }
            Board board = new Board(locations);
            return new ChessGame(board, turn);
        } catch (final SQLException e) {
            System.err.println("생성 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void delete() {
        final var query = "DELETE FROM chess_game";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (final SQLException e) {
            System.err.println("삭제 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
