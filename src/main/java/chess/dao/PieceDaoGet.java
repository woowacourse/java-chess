package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDaoGet extends PieceDao {
    private final String id;

    public PieceDaoGet(ConnectionGetter connectionGetter, String id) {
        super(connectionGetter);
        this.id = id;
    }

    @Override
    protected PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        ps = c.prepareStatement(
                "select * from pieces where id = ?");
        ps.setString(1, id);
        return ps;
    }
}
