package chess.domain.dao;

import java.sql.Connection;

public interface Connectable {
	Connection connect();

	void close(Connection con);
}
