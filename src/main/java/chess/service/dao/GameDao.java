package chess.service.dao;

import chess.controller.dto.GameDto;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;
import chess.domain.player.Turn;
import chess.view.web.PieceSymbolMapper;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameDao {
    private static final String COLUMN_LABEL_OF_TURN = "turn";
    private static final String COLUMN_LABEL_OF_BOARD = "board";
    private static final String SEPARATOR_OF_PIECE = ",";
    private final Connection conn;

    public GameDao(Connection connection) {
        this.conn = connection;
    }

    public static Board dataToBoard(final String dataLine) {
        final Map<Position, Piece> board = new HashMap<>();
        final String[] pieces = dataLine.split(SEPARATOR_OF_PIECE);
        int index = 0;
        for (final Horizontal h : Horizontal.values()) {
            for (final Vertical v : Vertical.values()) {
                board.put(new Position(v, h), PieceSymbolMapper.parseToPiece(pieces[index++]));
            }
        }
        return new Board(board);
    }

    public void save(final long roomId, final Turn turn, final Board board) throws SQLException {
        final String query = "INSERT INTO game_status (room, turn, board) VALUES (?, ?, ?)";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setLong(1, roomId);
        insertQuery.setString(2, turn.name());
        insertQuery.setString(3, boardToData(board));
        insertQuery.executeUpdate();
    }

    public GameDto load(final Long roomId) throws SQLException {
        final String query = "SELECT * FROM game_status WHERE room = (?) ORDER BY id DESC limit 1";
        final PreparedStatement insertQuery = conn.prepareStatement(query);
        insertQuery.setLong(1, roomId);
        return makeGameDto(insertQuery.executeQuery());
    }

    private GameDto makeGameDto(final ResultSet rs) throws SQLException {
        rs.next();
        final Turn turn = Turn.of(rs.getString(COLUMN_LABEL_OF_TURN));
        final Board board = dataToBoard(rs.getString(COLUMN_LABEL_OF_BOARD));
        return new GameDto(turn, board);
    }

    public void delete(final Long roomId) throws SQLException {
        final Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM game_status WHERE room = " + roomId);
    }

    public void update(final Long roomId, final Turn turn, final Board board) throws SQLException {
        final String query = "UPDATE game_status SET turn = ?,  board= ?  WHERE  room = ?";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setString(1, turn.name());
        insertQuery.setString(2, boardToData(board));
        insertQuery.setLong(3, roomId);
        insertQuery.executeUpdate();
    }

    public String boardToData(final Board board) {
        return Arrays.stream(board.parseUnicodeBoard())
                .flatMap(strings -> Arrays.stream(strings))
                .collect(Collectors.joining(SEPARATOR_OF_PIECE));
    }
}
