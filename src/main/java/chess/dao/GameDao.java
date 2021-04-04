package chess.dao;

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

    // XXX :: gameDTO를 이용해볼 생각
    public int save(final long roomId, final Turn turn, final Board board) throws SQLException {
        final String query = "INSERT INTO game_status (room, turn, board) VALUES (?, ?, ?)";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setLong(1, roomId);
        insertQuery.setString(2, turn.name());
        insertQuery.setString(3, boardToText(board));
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

    public GameDto load(final int roomId) throws SQLException {
        final GameDto gameDto = new GameDto();

        final String query = "SELECT * FROM game_status WHERE room = (?) ORDER BY id DESC limit 1";
        final PreparedStatement insertQuery = conn.prepareStatement(query);
        insertQuery.setString(1, String.valueOf(roomId));
        final ResultSet rs = insertQuery.executeQuery();

        rs.next();
        gameDto.setTurn(Turn.of(rs.getString("turn")));
        gameDto.setBoard(PieceSymbolMapper.parseToBoard(rs.getString("board")));

        return gameDto;
    }

    // XXX :: Service로 분리하기
    public String boardToText(final Board board) {
        final String separator = ",";
        final String[][] boardAsUnicode = PieceSymbolMapper.parseBoardAsUnicode(board);
        return Arrays.stream(boardAsUnicode)
                .flatMap(strings -> Arrays.stream(strings))
                .collect(Collectors.joining(separator));
    }
}
