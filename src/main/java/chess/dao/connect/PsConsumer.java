package chess.dao.connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PsConsumer {

    void accept(final PreparedStatement preparedStatement) throws SQLException;
}
