package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.Team;
import chess.domain.Turn;
import chess.dto.TurnDto;

public class TurnDao {
    public static void add(TurnDto turnDTO) throws SQLException {
        String query = "INSERT turn(team) VALUES(?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        Turn turn = turnDTO.getTurn();
        pstmt.setString(1, turn.toString());
        pstmt.executeUpdate();
    }

    public static TurnDto selectLastTurn() throws SQLException {
        String query = "SELECT team FROM turn ORDER BY id DESC LIMIT 0, 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();
        TurnDto turnDTO = new TurnDto();
        if (rs.next()) {
            turnDTO.setTurn(new Turn(Team.valueOf(rs.getString(1))));
        }
        return turnDTO;
    }

    public static void deleteAll() throws SQLException {
        String query = "TRUNCATE turn";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.execute();
    }

    public static void afterMove(TurnDto turnDTO) throws SQLException {
        deleteAll();
        add(turnDTO);
    }
}
