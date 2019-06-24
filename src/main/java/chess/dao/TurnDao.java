package chess.dao;

import chess.dto.TurnDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    public void add(TurnDto turnDto) throws SQLException {
        String query = "INSERT INTO turn VALUES (?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setInt(1, 1);
        pstmt.setString(2, turnDto.getTeam());
        pstmt.executeUpdate();
    }

    public void update(TurnDto turnDto) throws SQLException {
        String query = "UPDATE turn SET team = ? WHERE id = 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setString(1, turnDto.getTeam());
        pstmt.executeUpdate();
    }

    public TurnDto find() throws SQLException {
        String query = "SELECT team FROM turn WHERE id = 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        TurnDto turnDto = new TurnDto();
        if (rs.next()) {
            turnDto.setTeam(rs.getString(1));
        }
        return turnDto;
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM turn";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.executeUpdate();
    }
}
