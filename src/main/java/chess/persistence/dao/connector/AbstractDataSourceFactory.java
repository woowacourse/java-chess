package chess.persistence.dao.connector;

import javax.sql.DataSource;

public interface AbstractDataSourceFactory {
    DataSource create();

}
