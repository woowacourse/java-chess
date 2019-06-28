package chess.dao;

import chess.domain.board.Tile;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.dto.ChessBoardDto;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {
    private static final String insertQuery = "INSERT INTO chess_board (game_id, tile, piece_type, piece_color) VALUES (?, ?, ?, ?)";
    private static final String selectQuery = "SELECT tile, piece_type, piece_color FROM chess_board WHERE game_id=?";
    private static final String deleteQuery = "DELETE FROM chess_board WHERE game_id=?";
    private static ChessBoardDao chessBoardDAO;

    private ChessBoardDao() {
    }

    public static ChessBoardDao getInstance() {
        if (chessBoardDAO == null) {
            chessBoardDAO = new ChessBoardDao();
        }
        return chessBoardDAO;
    }

    public ChessBoardDto selectChessBoard(int id) {
            PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, id);
            RowMapper rm = rs -> {
                    Map<Tile, Piece> boardState = new HashMap<>();
                    while (rs.next()) {
                            Tile tile = Tile.of(rs.getString("tile"));
                            PieceType type = PieceType.valueOf(rs.getString("piece_type"));
                            PieceColor color = PieceColor.valueOf(rs.getString("piece_color"));
                            boardState.put(tile, type.generate(color));
                    }
                    return new ChessBoardDto(boardState);
            };

            JdbcTemplate template = new JdbcTemplate();
            return (ChessBoardDto) template.executeQuery(selectQuery, pss, rm);
    }

        public void insertChessBoard(int id, ChessBoardDto chessBoardDTO) {
                PreparedStatementSetter pss = pstmt -> {
                        pstmt.setInt(1, id);
                        Map<Tile, Piece> board = chessBoardDTO.getBoard();
                        for (Tile tile : board.keySet()) {
                                pstmt.setString(2, tile.toString());
                                pstmt.setString(3, String.valueOf(board.get(tile).getType()));
                                pstmt.setString(4, String.valueOf(board.get(tile).getColor()));
                        }
                };
                JdbcTemplate template = new JdbcTemplate();
                template.executeUpdate(insertQuery, pss);
    }

        public void deleteChessBoard(int id) {
                PreparedStatementSetter pss = pstmt -> {
                        pstmt.setInt(1, id);
                };
                JdbcTemplate template = new JdbcTemplate();
                template.executeUpdate(deleteQuery, pss);
    }
}
