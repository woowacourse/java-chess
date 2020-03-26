package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pawn extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.PAWN;
        for (Color color : Color.values()) {
            CACHE.put(color, new Pawn(color, type));
        }
    }

    public Pawn(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<Square> getAllCheatSheet(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        int index = 1;
        if (isBlack()) {
            index *= -1;
        }
        if ((isBlack() && square.getRank() == 7) ||
                (!isBlack() && square.getRank() == 2)) {
            availableSquares.add(square.addIfInBoundary(0, index * 2));
        }
        availableSquares.add(square.addIfInBoundary(0, index));
        return availableSquares;
    }
}
