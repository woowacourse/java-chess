package chess.model.repository.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {

    void setParameter(PreparedStatement pstmt) throws SQLException;
}
