package chess.dao;

import chess.dto.ResultDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDao {
    public void add(ResultDto resultDto) throws SQLException {
        String query = "INSERT INTO result VALUES (1, ?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setDouble(1, resultDto.getWhiteScore());
        pstmt.setDouble(2, resultDto.getBlackScore());

        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            update(resultDto);
        }
    }

    public ResultDto find() throws SQLException {
        String query = "SELECT white, black FROM result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        ResultDto resultDto = new ResultDto();
        if (rs.next()) {
            resultDto.setWhiteScore(rs.getDouble(1));
            resultDto.setBlackScore(rs.getDouble(2));
        }

        return resultDto;
    }

    public void update(ResultDto resultDto) throws SQLException {
        String query = "UPDATE result SET white = ?, black = ? WHERE id = 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setDouble(1, resultDto.getWhiteScore());
        pstmt.setDouble(2, resultDto.getBlackScore());
        pstmt.executeUpdate();
    }
}
