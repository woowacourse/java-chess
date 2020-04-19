package chess.web.repository;

import java.sql.Connection;

public interface ConnectionManager {

	Connection getConnection();

}
