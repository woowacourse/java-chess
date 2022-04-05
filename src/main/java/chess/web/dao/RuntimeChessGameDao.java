package chess.web.dao;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class RuntimeChessGameDao {
    private Map<Square, Piece> table = new HashMap<>();
    private Color turn = null;

    public void save(Square square, Piece piece) {
        table.put(square, piece);
    }

    public Map<Square, Piece> getAllPieces() {
        return table;
    }

    public void saveAll(Map<Square, Piece> board, Color turn) {
        this.table = board;
        this.turn = turn;
    }

    public Color getTurn() {
        return turn;
    }
}
