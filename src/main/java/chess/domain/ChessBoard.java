package chess.domain;

import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> cells = new LinkedHashMap<>();

    public ChessBoard(List<Piece> blackPieces, List<Piece> whitePieces) {
        init(blackPieces, whitePieces);
    }

    private void init(List<Piece> blackPieces, List<Piece> whitePieces) {
        for (Piece piece : blackPieces) {
            cells.put(piece.getPosition(), piece);
        }

        for (Piece piece : whitePieces) {
            cells.put(piece.getPosition(), piece);
        }
    }

    public int countPieces() {
        return cells.size();
    }

    public Map<Position, Piece> getCells() {
        return cells;
    }
}
