package chess.dao;

import chess.domain.board.Tile;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.dto.ChessBoardDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardDAO {
    private static final String insertQuery = "INSERT INTO chess_board (game_id, tile, piece_type, piece_color) VALUES (?, ?, ?, ?)";
    private static final String selectQuery = "SELECT tile, piece_type, piece_color FROM chess_board WHERE game_id=?";
    private static final String deleteQuery = "DELETE FROM chess_board WHERE game_id=?";

    private static ChessBoardDAO instance;

    private JdbcTemplate jdbcTemplate;

    private ChessBoardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static ChessBoardDAO getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new ChessBoardDAO(jdbcTemplate);
        }

        if (!instance.jdbcTemplate.equals(jdbcTemplate)) {
            instance.jdbcTemplate = jdbcTemplate;
        }
        return instance;
    }

    public ChessBoardDTO selectChessBoard(int id) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(id);

        ResultSet rs = jdbcTemplate.executeQuery(selectQuery, parameters);

        Map<Tile, Piece> boardState = new HashMap<>();
        while (rs.next()) {
            Tile tile = Tile.of(rs.getString("tile"));
            PieceType type = PieceType.valueOf(rs.getString("piece_type"));
            PieceColor color = PieceColor.valueOf(rs.getString("piece_color"));
            boardState.put(tile, type.generate(color));
        }

        return new ChessBoardDTO(boardState);
    }

    public void insertChessBoard(int id, ChessBoardDTO chessBoardDTO) throws SQLException {
        Map<Tile, Piece> board = chessBoardDTO.getBoard();
        for (Tile tile : board.keySet()) {
            List<Object> parameters = new ArrayList();

            parameters.add(id);
            parameters.add(tile.toString());
            parameters.add(board.get(tile).getType().toString());
            parameters.add(board.get(tile).getColor().toString());

            jdbcTemplate.executeUpdate(insertQuery, parameters);
        }
    }

    public void deleteChessBoard(int id) throws SQLException {
        List<Object> parameters = new ArrayList();
        parameters.add(id);

        jdbcTemplate.executeUpdate(deleteQuery, parameters);
    }
}
