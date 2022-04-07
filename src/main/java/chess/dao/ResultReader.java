package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultReader {

    private static final String READ_RESULT_EXCEPTION_MESSAGE = "잘못된 방식으로 조회 결과를 작업하였습니다.";

    private final ResultSet resultSet;

    public ResultReader(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public boolean hasNextRow() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public ResultReader nextRow() {
        try {
            resultSet.next();
            return this;
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public String readStringAt(String columnLabel) {
        try {
            return resultSet.getString(columnLabel);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public int readFirstColumn() {
        try {
            nextRow();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }
}