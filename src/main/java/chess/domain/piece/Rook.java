package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Rook extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.ROOK;
        for (Color color : Color.values()) {
            CACHE.put(color, new Rook(color, type));
        }
    }

    public Rook(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<Square> getAllCheatSheet(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        for (int index = -7; index < 8; index++) {
            availableSquares.add(square.addIfInBoundary(index, 0));
            availableSquares.add(square.addIfInBoundary(0, index));
        }
        availableSquares.remove(square);
        return availableSquares;
    }
}
