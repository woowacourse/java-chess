package chess.domain.dao;

import javax.sql.DataSource;

public interface AbstractDataSourceFactory {
    DataSource create();

}
