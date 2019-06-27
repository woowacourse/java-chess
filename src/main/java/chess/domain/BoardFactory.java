package chess.domain;

import chess.domain.pieces.Blank;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardFactory {
    private static final int START = 0;
    private static final int MAX_BOARD_SIZE = 8;
    private static final char X_START_INDEX = 'a';

    public static Map<Point, Piece> init() {
        Map<Point, Piece> pieces = new HashMap<>();
        List<Piece> black = PieceFactory.generateFirstPieces(Team.BLACK);
        List<Piece> white = PieceFactory.generateFirstPieces(Team.WHITE);
        for (int i = START; i < MAX_BOARD_SIZE; i++) {
            initBlackTeam(pieces, black, i);
            initBlank(pieces, i);
            initWhiteTeam(pieces, white, i);
        }
        return pieces;
    }

    private static void initWhiteTeam(Map<Point, Piece> pieces, List<Piece> white, int column) {
        pieces.put(new Point((char) (X_START_INDEX + column), '1'), white.get(column));
        pieces.put(new Point((char) (X_START_INDEX + column), '2'), new Pawn(Team.WHITE));
    }

    private static void initBlank(Map<Point, Piece> pieces, int column) {
        pieces.put(new Point((char) (X_START_INDEX + column), '3'), new Blank());
        pieces.put(new Point((char) (X_START_INDEX + column), '4'), new Blank());
        pieces.put(new Point((char) (X_START_INDEX + column), '5'), new Blank());
        pieces.put(new Point((char) (X_START_INDEX + column), '6'), new Blank());
    }

    private static void initBlackTeam(Map<Point, Piece> pieces, List<Piece> black, int column) {
        pieces.put(new Point((char) (X_START_INDEX + column), '7'), new Pawn(Team.BLACK));
        pieces.put(new Point((char) (X_START_INDEX + column), '8'), black.get(column));
    }
}
