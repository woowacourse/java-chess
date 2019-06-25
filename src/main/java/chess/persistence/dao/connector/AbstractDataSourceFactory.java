package chess.persistence.dao.connector;

import javax.sql.DataSource;
import java.sql.Connection;

public interface AbstractDataSourceFactory {
    DataSource create();

}
