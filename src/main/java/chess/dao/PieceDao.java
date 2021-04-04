package chess.dao;

import chess.domain.exceptions.DatabaseException;
import chess.domain.piece.Piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.dao.DbConnection.getConnection;

public class PieceDao {

    public int findPieceId(Piece piece) {
        String query = "SELECT pid FROM piece WHERE piece_kind = ? AND piece_color = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithTwoParameter(
                con.prepareStatement(query), piece.symbol().toUpperCase(), piece.color().getName());
            ResultSet rs = pstmt.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("pid");
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private PreparedStatement createPreparedStatementWithTwoParameter(
        PreparedStatement ps, String firstParam, String secondParam) throws SQLException {
        ps.setString(1, firstParam);
        ps.setString(2, secondParam);
        return ps;
    }
}
