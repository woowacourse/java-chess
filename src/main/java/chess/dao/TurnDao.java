package chess.dao;

import chess.domain.Team;
import chess.domain.Turn;
import chess.dto.TurnDto;
import jdk.nashorn.internal.scripts.JD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    public static void add(TurnDto turnDto) throws SQLException {
        String query = "INSERT INTO turn VALUES (?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setString(1, turnDto.getTeam().name());
        pstmt.executeUpdate();
    }

    public static void update(TurnDto turnDto) throws SQLException {
        String query = "UPDATE turn SET team = ? WHERE id = 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setString(1, turnDto.getTeam().name());
        pstmt.executeUpdate();
    }

    public static TurnDto find() throws SQLException {
        String query = "SELECT team FROM turn WHERE id = 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        TurnDto turnDto = new TurnDto();
        if (rs.next()) {
            turnDto.setTeam(Team.valueOf(rs.getString(1)));
        }
        return turnDto;
    }

    public static void delete() throws SQLException {
        String query = "DELETE FROM turn";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.executeUpdate();
    }
}
