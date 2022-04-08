package chess.dao;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BoardDao {
    private final DatabaseConnector databaseConnector;

    public BoardDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public void save(Map<Position, Piece> board) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        final String sql = chooseSaveSql();

        PreparedStatement statement = connection.prepareStatement(sql);
        for (Entry<Position, Piece> entry : board.entrySet()) {
            savePiece(statement, entry);
        }
        DatabaseConnector.close(connection, statement);
    }

    private String chooseSaveSql() throws SQLException {
        String sql = "insert into piece (game_no, type, white, position) values (1, ?, ?, ?)";
        if (isBoardExistIn()) {
            sql = "update piece set type = ?, white = ? where position = ?";
        }
        return sql;
    }

    private void savePiece(PreparedStatement statement, Entry<Position, Piece> entry) throws SQLException {
        Piece piece = entry.getValue();
        statement.setString(1, piece.getType().toString());
        statement.setBoolean(2, piece.isCamp(Camp.WHITE));
        statement.setString(3, entry.getKey().toString());
        statement.execute();
    }

    private boolean isBoardExistIn() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        final String sql = "select no from piece";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        boolean boardExist = resultSet.next();
        DatabaseConnector.close(connection, statement, resultSet);
        return boardExist;
    }

    public Map<String, PieceDto> load() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        final String sql = "select type, white, position from piece";

        Map<String, PieceDto> board = new TreeMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            putPiece(board, resultSet);
        }
        DatabaseConnector.close(connection, statement, resultSet);
        return board;
    }

    private void putPiece(Map<String, PieceDto> board, ResultSet resultSet) throws SQLException {
        PieceDto piece = PieceDto.of(
                resultSet.getString("type"),
                resultSet.getBoolean("white")
        );
        board.put(resultSet.getString("position"), piece);
    }
}
