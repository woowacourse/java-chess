package chess.dao;

import chess.dto.board.BoardInformationDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDao {

    public int save(final String turn, final Connection connection) throws SQLException {
        final String sql = "insert into board (turn) values (?)";

        final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, turn);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        validResultSet(resultSet);

        return resultSet.getInt(1);
    }

    private void validResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException("쿼리문 실행 결과가 존재하지 않습니다.");
        }
    }

    public BoardInformationDto findRecentBoard(final Connection connection) throws SQLException {
        final String sql = "select * from board order by id desc limit 1";

        final PreparedStatement statement = connection.prepareStatement(sql);
        final ResultSet resultSet = statement.executeQuery();

        System.out.println(resultSet.toString());

        if (!resultSet.next()) {
           throw new SQLException("");
        }

        final String turn = resultSet.getString("turn");
        final int id = resultSet.getInt("id");
        return new BoardInformationDto(id, turn);
    }
}
