package chess.dao;

import chess.dao.setting.DBConnection;
import chess.dto.TurnChangeRequestDto;
import chess.dto.TurnRequestDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnDao extends DBConnection {
    public void initializeTurn() throws SQLException {
        String query = "INSERT INTO turn (current_turn) VALUE (?)";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, "white");
        psmt.executeUpdate();
    }

    public List<TurnRequestDto> showCurrentTurn() throws SQLException {
        List<TurnRequestDto> turn = new ArrayList<>();
        ResultSet rs = readCurrentTurn();
        while (rs.next()) {
            turn.add(new TurnRequestDto(
                    rs.getLong("id"),
                    rs.getString("current_turn")
            ));
        }
        return turn;
    }

    private ResultSet readCurrentTurn() throws SQLException {
        String query = "SELECT * FROM turn";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        return psmt.executeQuery();
    }

    public void changeTurn(final TurnChangeRequestDto turnChangeRequestDto) throws SQLException {
        String query = "UPDATE turn SET current_turn=? WHERE current_turn=?";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, turnChangeRequestDto.getNextTurn());
        psmt.setString(2, turnChangeRequestDto.getCurrentTurn());
        psmt.executeUpdate();
    }

    public void removeTurn() throws SQLException {
        String query = "DELETE FROM turn";
        PreparedStatement pstm = getConnection().prepareStatement(query);
        pstm.executeUpdate();
    }
}
