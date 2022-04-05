package chess.web.dao;

import static chess.web.dao.DBConnector.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessBoardDao {
    public int save() {
        try {
            return saveChessBoard();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int saveChessBoard() throws SQLException {
        Connection connection = getConnection();
        String sql = "insert into chessboard values ()";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();

        return resultSet.getInt(1);
    }
}
