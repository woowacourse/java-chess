package chess.dao;

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
}
