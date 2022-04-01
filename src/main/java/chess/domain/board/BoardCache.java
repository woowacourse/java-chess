package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardCache {

    private static final Map<Position, Piece> CACHE = new LinkedHashMap<>();
    private static final String NOT_EXIST_POSITION = "존재하지 않는 좌표입니다.";
    private static final int BLACK_PIECE = 0;
    private static final int BLACK_PAWN = 1;
    private static final int WHITE_PIECE = 6;
    private static final int WHITE_PAWN = 7;

    static {
        initializePiece(BLACK_PIECE, Color.BLACK);
        initializePiecePawn(BLACK_PAWN, Color.BLACK);
        initializePiecePawn(WHITE_PIECE, Color.WHITE);
        initializePiece(WHITE_PAWN, Color.WHITE);
    }

    public static Map<Position, Piece> create() {
        return new LinkedHashMap<>(CACHE);
    }

    private static void initializePiece(int row, Color color) {
        CACHE.put(new Position(row, 0), new Rook(color));
        CACHE.put(new Position(row, 1), new Knight(color));
        CACHE.put(new Position(row, 2), new Bishop(color));
        CACHE.put(new Position(row, 3), new Queen(color));
        CACHE.put(new Position(row, 4), new King(color));
        CACHE.put(new Position(row, 5), new Bishop(color));
        CACHE.put(new Position(row, 6), new Knight(color));
        CACHE.put(new Position(row, 7), new Rook(color));
    }

    private static void initializePiecePawn(int row, Color color) {
        for (int index = 0; index < Chessboard.SIZE.size(); index++) {
            CACHE.put(new Position(row, index), new Pawn(color));
        }
    }

    public static Position findPosition(int row, int column) {
        return CACHE.keySet()
                .stream()
                .filter(position -> position.isSamePosition(row, column))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_POSITION));
    }
}
