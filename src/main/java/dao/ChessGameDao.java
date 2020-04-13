package dao;

import db.DBConnection;
import dto.ChessGameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    public ChessGameDto findChessGameBy(int gameId) throws SQLException {
        String query = "SELECT * FROM game WHERE id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new ChessGameDto(
                    rs.getInt("id"),
                    rs.getString("white_name"),
                    rs.getString("black_name"),
                    rs.getInt("turn_is_black")
            );
        }
    }
}
