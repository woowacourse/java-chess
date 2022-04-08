package chess.dao;

import java.sql.PreparedStatement;

public class PieceDao {

    public int getIdFrom(final String symbol, final String color) {
        final String sql = "SELECT id FROM piece WHERE symbol=? AND color=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setString(1, symbol);
            statement.setString(2, color);
        });
        return CommonDao.findId(sql, statementMaker, "id");
    }
}
