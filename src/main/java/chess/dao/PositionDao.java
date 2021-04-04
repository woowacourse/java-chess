package chess.dao;

import chess.domain.board.Position;
import chess.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.dao.DbConnection.getConnection;

public class PositionDao {

    public int findPositionId(Position position) {
        String query = "SELECT pid FROM position WHERE address = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithOneParameter(
                con.prepareStatement(query), position.toString());
            ResultSet rs = pstmt.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("pid");
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private PreparedStatement createPreparedStatementWithOneParameter(
        PreparedStatement ps, String param) throws SQLException {
        ps.setString(1, param);
        return ps;
    }
}
