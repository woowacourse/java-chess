package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.chessboard.SquareStatus;
import domain.coordinate.Position;
import domain.piece.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao implements JdbcDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private int id;

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public int save(ChessGame chessGame) {
        try (final Connection connection = getConnection()) {
            String id = getLastInsertId(connection);
            saveChessGame(connection);
            savePieces(chessGame.getChessBoard(), connection, id);
            return Integer.parseInt(id);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private String getLastInsertId(Connection connection) {
        try {
            return getCurrentId(connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private String getCurrentId(Connection connection) throws SQLException {
        PreparedStatement lastInsertIdSelect = connection.prepareStatement("SELECT LAST_INSERT_ID() AS id");
        ResultSet resultSet = lastInsertIdSelect.executeQuery();
        String lastInsertId = null;

        if (resultSet.next()) {
            lastInsertId = resultSet.getString("id");
        }

        return lastInsertId;
    }

    private void saveChessGame(Connection connection) throws SQLException {
        PreparedStatement chessGameSave = connection.prepareStatement(
                "INSERT INTO chess_game(turn) VALUES(?)"
        );
        // 이렇게 하고서 AutoIncrement 를 가져올 수 있어야함

        chessGameSave.setString(1, Color.WHITE.name());
        chessGameSave.executeUpdate();
    }

    private void savePieces(ChessBoard chessBoard, Connection connection, String currentId) throws SQLException {
        PreparedStatement pieceSave = connection.prepareStatement(
                "INSERT INTO chess_board(x, y, piece_type, piece_color, game_id) VALUES(?, ?, ?, ?, ?)"
        );
        for (int x = 0; x < 8; x++) {
            saveColumnPieces(chessBoard, currentId, pieceSave, x);
        }
    }

    private void saveColumnPieces(ChessBoard chessBoard, String currentId, PreparedStatement pieceSave, int x) throws SQLException {
        for (int y = 0; y < 8; y++) {
            SquareStatus squareStatus = chessBoard.findSquare(Position.of(x, y)).getSquareStatus();
            pieceSave.setString(1, Integer.toString(x));
            pieceSave.setString(2, Integer.toString(y));
            pieceSave.setString(3, squareStatus.getType().name());
            pieceSave.setString(4, squareStatus.getColor().name());
            pieceSave.setString(5, currentId);
            pieceSave.executeUpdate();
        }
    }

    @Override
    public ChessGame selectNewGame(ChessGame chessGame) {
        return null;
    }

    @Override
    public ChessGame select() { // join 해서, 가져온다
        return null;
    }

    @Override
    public void update() { // update 는 이제, turn, (Position, Square)

    }

    @Override
    public void delete() { // cascade 로 생성해서, 그냥 delete game id

    }

}
