package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
        public void executeUpdate(String sql, PreparedStatementSetter pss) {
                try (Connection connection = DBUtil.getConnection();
                     PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        pss.setParameters(pstmt);

                        pstmt.executeUpdate();
                } catch (SQLException e) {
                        throw new RuntimeException("executeUpdate 오류");
                }
        }

        public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rm) {
                try (Connection connection = DBUtil.getConnection();
                     PreparedStatement pstmt = createSelectPstmt(connection, sql, pss);
                     ResultSet rs = pstmt.executeQuery()) {
                        return rm.mapRow(rs);
                } catch (SQLException e) {
                        throw new RuntimeException("executeQuery 오류");
                }
        }

        private PreparedStatement createSelectPstmt(Connection connection, String sql, PreparedStatementSetter pss) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pss.setParameters(pstmt);
                return pstmt;
        }
}
