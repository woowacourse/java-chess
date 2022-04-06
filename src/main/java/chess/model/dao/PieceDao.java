package chess.model.dao;

import chess.model.Color;
import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.pawn.Pawn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDao {
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

    public Piece findById(int id) {
        Connection connection = getConnection();
        final String sql = "select * from piece where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return getPieceBy(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Piece getPieceBy(ResultSet resultSet) {
        String typeName = null;
        String colorName = null;
        try {
            typeName = resultSet.getString(2);
            colorName = resultSet.getString(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Color color = Color.valueOf(colorName.toUpperCase());
        if (typeName.equals("pawn")) {
            return Pawn.of(color);
        }
        if (typeName.equals("king")) {
            return new King(color);
        }
        if (typeName.equals("queen")) {
            return new Queen(color);
        }
        if (typeName.equals("knight")) {
            return new Knight(color);
        }
        if (typeName.equals("rook")) {
            return new Rook(color);
        }
        if (typeName.equals("bishop")) {
            return new Bishop(color);
        }
        return new Empty();
    }
}
