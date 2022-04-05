package chess.db;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.web.MyState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDAO {
    private static String URL = "jdbc:mysql://localhost:3306/chess";

    public Connection getConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(URL, "user", "password");
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private void loadDriver() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void initializeDB(MyState myState) {
        Map<Position, Piece> pieces = myState.getBoard().getPieces();
        for (Position position : pieces.keySet()) {
            addOnePiece(position, pieces.get(position));
        }
    }

    private void addOnePiece(Position position, Piece piece) {
        Connection connection = getConnection();
        String sql = "insert into board (location, name, color, turnColor) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getPosition());
            statement.setString(2, piece.getName());
            statement.setString(3, piece.getColor());
            statement.setString(4, Color.WHITE.name());
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void terminateDB() {
        Connection connection = getConnection();
        String sql = "delete from board";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isSaved() {
        Connection connection = getConnection();
        String sql = "select * from board";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException | NullPointerException exception) {
            return false;
        }
    }

    public Color findTurn() {
        Connection connection = getConnection();
        String sql = "select distinct turnColor from board";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return Color.valueOf(resultSet.getString("turnColor"));
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Board findAllPieces() {
        Connection connection = getConnection();
        String sql = "select location, name, color from board";
        Map<Position, Piece> pieces = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pieces.put(new Position(resultSet.getString("location")),
                        PieceGenerator.of(resultSet.getString("name"), resultSet.getString("color")));
            }
            return new Board(pieces);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void insert(Position position, Piece piece, Color turn) {
        Connection connection = getConnection();
        String sql = "insert into board (location, name, color, turnColor) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getPosition());
            statement.setString(2, piece.getName());
            statement.setString(3, piece.getColor());
            statement.setString(4, turn.name());
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void delete(Position position) {
        Connection connection = getConnection();
        String sql = "delete from board where location = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getPosition());
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void convertOneItem(Color color) {
        Connection connection = getConnection();
        String sql = "update board set turnColor = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, color.name());
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isExist(Position position) {
        Connection connection = getConnection();
        String sql = "select * from board where location = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getPosition());
            return statement.executeQuery().next();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
