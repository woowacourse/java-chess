package chess.web.dao;

import static chess.web.dao.DBConnector.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessBoardDao {
    public int save() {
        try (Connection connection = getConnection()) {
            return saveChessBoard(connection);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int saveChessBoard(Connection connection) throws SQLException {
        String sql = "insert into chessboard values ()";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();

        return resultSet.getInt(1);
    }
}
