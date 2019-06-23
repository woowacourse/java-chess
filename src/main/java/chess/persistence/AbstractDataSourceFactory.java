package chess.persistence;

import javax.sql.DataSource;

@FunctionalInterface
public interface AbstractDataSourceFactory {
    DataSource createDataSource();
}
