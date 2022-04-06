package chess.dao;

import chess.model.Color;
import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.PieceLetter;
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

    public Piece findById(int id) {
        Connection connection = JdbcUtil.getConnection();
        final String sql = "select * from piece where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
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
            resultSet.close();
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

    public int findIdByPiece(Piece piece) {
        final String sql = "select id from piece where type = ? and color = ?";
        String pieceType = PieceLetter.getName(piece);
        String colorName = piece.getColor().name().toLowerCase();
        try(Connection connection = JdbcUtil.getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceType);
            statement.setString(2, colorName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
