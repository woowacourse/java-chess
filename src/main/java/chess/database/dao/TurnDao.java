package chess.database.dao;

import chess.database.DBConnector;
import chess.domain.Turn;

import java.sql.*;

public class TurnDao {
    private static TurnDao INSTANCE = new TurnDao();
    private final Connection connection;

    public static TurnDao getInstance() {
        if (INSTANCE == null) return new TurnDao();
        return INSTANCE;
    }

    private TurnDao() {
        this.connection = DBConnector.getConnection();
    }


    public Turn load() throws SQLException {
        final String sql = "SELECT black_turn FROM turn";
        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {
            boolean blackTurn = false;
            while (rs.next()) {
                blackTurn = rs.getBoolean("black_turn");
            }
            return Turn.of(blackTurn);
        }

    }

    public void save(Turn turn) throws SQLException{
        String sql = "INSERT INTO turn(black_turn) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, turn.getTurn());
        statement.executeUpdate();
    }

    public void delete() throws SQLException {
        String sql = "DELETE FROM turn";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
}
