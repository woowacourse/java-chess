package web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
interface ResultSetExtractor<T> {
    T extractData(ResultSet rs) throws SQLException;
}
