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
        String sql = "insert into piece (no, game_no, type, white, position) values (0, 1, ?, ?, ?)";
        if (isBoardExist()) {
            sql = "update piece set type = ?, white = ? where position = ?";
        }

        PreparedStatement statement = connection.prepareStatement(sql);
        for (Entry<Position, Piece> entry : board.entrySet()) {
            Piece piece = entry.getValue();
            statement.setString(1, piece.getType());
            statement.setBoolean(2, piece.isCamp(Camp.WHITE));
            statement.setString(3, entry.getKey().toString());
            statement.execute();
        }
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isBoardExist() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = "select no from piece";
        boolean boardExist;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boardExist = resultSet.next();
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardExist;
    }

    public Map<Position, Piece> load() throws SQLException {
        Connection connection = DatabaseConnector.getConnection();
        final String sql = "select type, white, position from piece";

        Map<Position, Piece> board = new TreeMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            Type type = Type.from(resultSet.getString("type"));
            Camp camp = Camp.BLACK;
            if (resultSet.getBoolean("white")) {
                camp = Camp.WHITE;
            }
            Piece piece = type.generatePiece(camp);
            board.put(Position.of(resultSet.getString("position")), piece);
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }
}
