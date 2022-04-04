package chess.dao;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BoardDao {

    public void save(Map<Position, Piece> board) throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = chooseSaveSql();

        PreparedStatement statement = connection.prepareStatement(sql);
        for (Entry<Position, Piece> entry : board.entrySet()) {
            savePiece(statement, entry);
        }
        DatabaseConnector.close(connection, statement);
    }

    private String chooseSaveSql() throws SQLException {
        String sql = "insert into piece (no, game_no, type, white, position) values (0, 1, ?, ?, ?)";
        if (isBoardExist()) {
            sql = "update piece set type = ?, white = ? where position = ?";
        }
        return sql;
    }

    private void savePiece(PreparedStatement statement, Entry<Position, Piece> entry) throws SQLException {
        Piece piece = entry.getValue();
        statement.setString(1, piece.getType());
        statement.setBoolean(2, piece.isCamp(Camp.WHITE));
        statement.setString(3, entry.getKey().toString());
        statement.execute();
    }

    private boolean isBoardExist() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = "select no from piece";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boolean boardExist = resultSet.next();
        DatabaseConnector.close(connection, statement, resultSet);
        return boardExist;
    }

    public Map<Position, Piece> load() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = "select type, white, position from piece";

        Map<Position, Piece> board = new TreeMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            putPiece(board, resultSet);
        }
        DatabaseConnector.close(connection, statement, resultSet);
        return board;
    }

    private void putPiece(Map<Position, Piece> board, ResultSet resultSet) throws SQLException {
        Type type = Type.from(resultSet.getString("type"));
        Piece piece = type.generatePiece(loadCamp(resultSet));
        board.put(Position.of(resultSet.getString("position")), piece);
    }

    private Camp loadCamp(ResultSet resultSet) throws SQLException {
        Camp camp = Camp.BLACK;
        if (resultSet.getBoolean("white")) {
            camp = Camp.WHITE;
        }
        return camp;
    }
}
