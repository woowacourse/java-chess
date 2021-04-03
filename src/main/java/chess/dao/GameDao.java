package chess.dao;

import chess.controller.dto.BoardDto;
import chess.domain.board.Board;

import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameDao {
    private final Connection conn;

    public GameDao(Connection connection){
        this.conn = connection;
    }

    public int addGameStatus(final String turn, final BoardDto board) throws SQLException {
        final String query = "INSERT INTO game_status (turn, board) VALUES (?, ?)";
        PreparedStatement insertQuery = conn.prepareStatement(query);
        insertQuery.setString(1, turn);
        insertQuery.setString(2, boardData(board.getBoard()));
        insertQuery.executeUpdate();
        return getLastInsertId();
    }

    public int getLastInsertId() throws SQLException {
        final String query = "SELECT LAST_INSERT_ID()";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    // XXX :: Service로 분리하기
    public String boardData(final String[][] board) {
        final String separator = ",";
        return Arrays.stream(board)
                .flatMap(strings -> Arrays.stream(strings))
                .collect(Collectors.joining(separator));
    }
}
