package chess.domain.board;

import chess.domain.piece.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class PieceCache {

    private static final Map<String, Piece> CACHE = new LinkedHashMap<>();

    static {
        initializePiece(0, Color.BLACK);
        initializePiecePawn(1, Color.BLACK);
        IntStream.range(2, 6)
                .forEach(PieceCache::initializeBlank);
        initializePiecePawn(6, Color.WHITE);
        initializePiece(7, Color.WHITE);
    }

    public Piece of(String row, String column) {
        return CACHE.get(row + column);
    }

    public static Map<String, Piece> create() {
        return new LinkedHashMap<>(CACHE);
    }

    private static void initializePiece(int row, Color color) {
        CACHE.put(row + Integer.toString(0), new Rook(color));
        CACHE.put(row + Integer.toString(1), new Knight(color));
        CACHE.put(row + Integer.toString(2), new Bishop(color));
        CACHE.put(row + Integer.toString(3), new Queen(color));
        CACHE.put(row + Integer.toString(4), new King(color));
        CACHE.put(row + Integer.toString(5), new Bishop(color));
        CACHE.put(row + Integer.toString(6), new Knight(color));
        CACHE.put(row + Integer.toString(7), new Rook(color));
    }

    private static void initializePiecePawn(int row, Color color) {
        for (int index = 0; index < Chessboard.SIZE.size(); index++) {
            CACHE.put(row + Integer.toString(index), new Pawn(color));
        }
    }

    private static void initializeBlank(int row) {
        for (int index = 0; index < Chessboard.SIZE.size(); index++) {
            CACHE.put(row + Integer.toString(index), new Blank());
        }
    }
}
