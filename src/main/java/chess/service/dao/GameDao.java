package chess.service.dao;

import chess.controller.dto.GameDto;
import chess.domain.board.Board;
import chess.domain.player.Turn;
import chess.service.PieceSymbolMapper;

import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameDao {
    private final Connection conn;

    public GameDao(Connection connection) {
        this.conn = connection;
    }

    public void save(final long roomId, final Turn turn, final Board board) throws SQLException {
        final String query = "INSERT INTO game_status (room, turn, board) VALUES (?, ?, ?)";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setLong(1, roomId);
        insertQuery.setString(2, turn.name());
        insertQuery.setString(3, boardToText(board));
        insertQuery.executeUpdate();
    }

    public GameDto load(final Long roomId) throws SQLException {
        final String query = "SELECT * FROM game_status WHERE room = (?) ORDER BY id DESC limit 1";
        final PreparedStatement insertQuery = conn.prepareStatement(query);
        insertQuery.setLong(1, roomId);

        final ResultSet rs = insertQuery.executeQuery();
        rs.next();

        final Turn turn = Turn.of(rs.getString("turn"));
        final Board board = PieceSymbolMapper.parseToBoard(rs.getString("board"));
        final GameDto gameDto = new GameDto();
        gameDto.setTurn(turn);
        gameDto.setBoard(board);

        return gameDto;
    }

    public void delete(final Long roomId) throws SQLException {
        final Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM game_status WHERE room = " + roomId);
    }

    public String boardToText(final Board board) {
        final String separator = ",";
        final String[][] boardAsUnicode = board.parseUnicodeBoard();
        return Arrays.stream(boardAsUnicode)
                .flatMap(strings -> Arrays.stream(strings))
                .collect(Collectors.joining(separator));
    }

    public void update(final Long roomId, final Turn turn, final Board board) throws SQLException {
        final String query = "UPDATE game_status SET turn = ?,  board= ?  WHERE  room = ?";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setString(1, turn.name());
        insertQuery.setString(2, boardToText(board));
        insertQuery.setLong(3, roomId);
        insertQuery.executeUpdate();
    }
}
