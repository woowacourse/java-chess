package chess.persistence;

import javax.sql.DataSource;

public interface AbstractDataSourceFactory {

    DataSource createDataSource();
}
