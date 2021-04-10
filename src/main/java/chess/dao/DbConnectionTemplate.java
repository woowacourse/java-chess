package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DbConnectionTemplate {
    interface PsConsumer {
        void accept(PreparedStatement preparedStatement) throws SQLException;
    }

    interface RsFunction<T> {
        T apply(ResultSet resultSet) throws SQLException;
    }

    public static <T> T executeQuery(String query, PsConsumer setParams, RsFunction<T> createDtoFunction) {
        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParams.accept(preparedStatement);

            if (Objects.isNull(createDtoFunction)) {
                preparedStatement.executeUpdate();
                return null;
            }

            preparedStatement.executeQuery();
            return createDtoFunction.apply(preparedStatement.getResultSet());
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static <T> T executeQuery(String query, PsConsumer setParams) {
        return executeQuery(query, setParams, null);
    }

    public static <T> T executeQueryWithGenerateKey(String query, PsConsumer setParams, RsFunction<T> createDtoFunction) {
        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParams.accept(preparedStatement);

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return createDtoFunction.apply(generatedKeys);
            }
            throw new IllegalStateException("생성된 키가 없습니다.");
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
