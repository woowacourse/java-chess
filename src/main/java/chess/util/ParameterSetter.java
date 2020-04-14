package chess.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetter {
    void setParameters(PreparedStatement pstmt) throws SQLException;
}
