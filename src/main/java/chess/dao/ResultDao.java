package chess.dao;

import chess.dto.ResultDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDao {

    private static ResultDao instance;

    private ResultDao() {
    }

    public static ResultDao getInstance() {
        if (instance == null) {
            instance = new ResultDao();
        }
        return instance;
    }

    public void add(ResultDto resultDto) throws SQLException {
        String query = "INSERT INTO result VALUES (?, ?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setString(1, resultDto.getName());
        pstmt.setString(2, resultDto.getTeam());
        pstmt.setInt(3, resultDto.getCount());

        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            update(resultDto);
        }
    }

    public List<ResultDto> find() throws SQLException {
        String query = "SELECT * FROM result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<ResultDto> resultDtos = new ArrayList<>();
        while (rs.next()) {
            ResultDto resultDto = new ResultDto();
            resultDto.setName(rs.getString(1));
            resultDto.setTeam(rs.getString(2));
            resultDto.setCount(rs.getInt(3));

            resultDtos.add(resultDto);
        }

        return resultDtos;
    }

    public void update(ResultDto resultDto) throws SQLException {
        if (resultDto.getName() == null && resultDto.getTeam() == null) {
            return;
        }

        String query = "INSERT INTO result VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE count = count + 1";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.setString(1, resultDto.getName());
        pstmt.setString(2, resultDto.getTeam());
        pstmt.setInt(3, 1);

        pstmt.executeUpdate();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        pstmt.executeUpdate();
    }
}
