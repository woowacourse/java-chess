package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.GameTurn;
import chess.domain.board.SavedBoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class ChessGameDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String gameId, ChessGame chessGame) {
        String sql = "insert into chessGame (gameID, turn) values (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameId);
            statement.setString(2, chessGame.getTurn().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTurn(String gameId, ChessGame chessGame) {
        String sql = "update chessGame set turn = ? where gameID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chessGame.getTurn().name());
            statement.setString(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void find(String gameID, ChessGame chessGame) {
        String selectPiecesSql = "select position, type, color from piece where gameID = ?";
        Map<Square, Piece> board = new HashMap<>();
        try (Connection connection = getConnection();
             PreparedStatement piecesStatement = getPreparedStatement(gameID, selectPiecesSql, connection);
             ResultSet resultPiecesSet = piecesStatement.executeQuery()) {
            while (resultPiecesSet.next()) {
                String position = resultPiecesSet.getString("position");
                String type = resultPiecesSet.getString("type");
                String color = resultPiecesSet.getString("color");
                board.put(new Square(position), Piece.createByTypeAndColor(type, color));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (board.isEmpty()) {
            throw new IllegalArgumentException("헉.. 저장 안한거 아냐? 그런 게임은 없어!");
        }
        String selectGameSql = "select turn from chessGame where gameID = ?";
        String turn = null;
        try (Connection connection = getConnection();
             PreparedStatement gameStatement = getPreparedStatement(gameID, selectGameSql, connection);
             ResultSet resultGameSet = gameStatement.executeQuery()) {
            if (!resultGameSet.next()) {
                return;
            }
            turn = resultGameSet.getString("turn");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chessGame.startGame(new SavedBoardGenerator(board), GameTurn.find(turn));
    }

    private PreparedStatement getPreparedStatement(String gameID, String selectPiecesSql, Connection connection) throws
            SQLException {
        PreparedStatement piecesStatement = connection.prepareStatement(selectPiecesSql);
        piecesStatement.setString(1, gameID);
        return piecesStatement;
    }
}
