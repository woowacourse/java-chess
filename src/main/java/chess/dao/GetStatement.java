package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatement implements StatementStrategy {
    private final String id;

    public GetStatement(String id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
        PreparedStatement ps;

        ps = c.prepareStatement(
                "select * from pieces where id = ?");
        ps.setString(1, id);
        return ps;
    }
}
