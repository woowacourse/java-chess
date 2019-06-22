package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.Team;
import chess.domain.Turn;
import chess.dto.TurnDTO;

public class TurnDAO {
    public static void add(TurnDTO turnDTO) throws SQLException {
        String query = "INSERT turn(team) VALUES(?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        Turn turn = turnDTO.getTurn();
        pstmt.setString(1, turn.toString());
        pstmt.executeUpdate();
    }

    public static TurnDTO selectLastTurn() throws SQLException {
        String query = "SELECT team FROM turn ORDER BY id DESC LIMIT 0, 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();
        TurnDTO turnDTO = new TurnDTO();
        if (rs.next()) {
            System.out.println(rs.getObject(1));
            turnDTO.setTurn(new Turn(Team.valueOf(rs.getString(1))));
        }
        return turnDTO;
    }

    public static void deleteAll() throws SQLException {
        String query = "TRUNCATE turn";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.execute();
    }

    public static void afterMove(TurnDTO turnDTO) throws SQLException {
        deleteAll();
        add(turnDTO);
    }
}
