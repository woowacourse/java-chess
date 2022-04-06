package chess.model.dao;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.position.Position;

import java.sql.*;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            System.out.println("커넥션 연결에 실패하였습니다.");
            e.printStackTrace();
        }
        return conn;
    }

    public void init(Board board) {
        String query = "insert into pieces (position, name) values (?, ?)";
        board.getBoard().forEach(((position, piece) -> {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, position.getPosition());
                preparedStatement.setString(2, piece.getPieceName());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }));
    }

    public Map<Position, Piece> findAll() {
        String query = "select position, name from pieces";
        Map<Position, Piece> board = new HashMap<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pieceResult = resultSet.getString("piece");
                Piece piece = PieceFactory.create(pieceResult);
                String positionResult = resultSet.getString("position");
                Position position = Position.from(positionResult);
                board.put(position, piece);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return board;
    }

    public String findByPosition(String source) {
        String query = "select name from pieces where position = (?)";
        String piece = "";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, source);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            piece = resultSet.getString("name");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return piece;
    }
}
