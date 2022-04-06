package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardCache {

    private static final Map<Position, Piece> CACHE = new LinkedHashMap<>();
    private static final String BLACK_PIECE = "8";
    private static final String BLACK_PAWN = "7";
    private static final String WHITE_PIECE = "2";
    private static final String WHITE_PAWN = "1";

    static {
        initializePiece(BLACK_PIECE, Color.BLACK);
        initializePiecePawn(BLACK_PAWN, Color.BLACK);
        initializePiecePawn(WHITE_PIECE, Color.WHITE);
        initializePiece(WHITE_PAWN, Color.WHITE);
    }

    private BoardCache() {
    }

    public static Map<Position, Piece> create() {
        return new LinkedHashMap<>(CACHE);
    }

    private static void initializePiece(String row, Color color) {
        CACHE.put(Position.of(row, "a"), new Rook(color));
        CACHE.put(Position.of(row, "b"), new Knight(color));
        CACHE.put(Position.of(row, "c"), new Bishop(color));
        CACHE.put(Position.of(row, "d"), new Queen(color));
        CACHE.put(Position.of(row, "e"), new King(color));
        CACHE.put(Position.of(row, "f"), new Bishop(color));
        CACHE.put(Position.of(row, "g"), new Knight(color));
        CACHE.put(Position.of(row, "h"), new Rook(color));
    }

    private static void initializePiecePawn(String row, Color color) {
        for (File file : File.values()) {
            CACHE.put(Position.of(row, file.file()), new Pawn(color));
        }
    }
}
