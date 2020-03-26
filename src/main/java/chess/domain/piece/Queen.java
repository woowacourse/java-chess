package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Queen extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.QUEEN;
        for (Color color : Color.values()) {
            CACHE.put(color, new Queen(color, type));
        }
    }

    public Queen(Color color, Type type) {
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
            availableSquares.add(square.addIfInBoundary(index * -1, index));
            availableSquares.add(square.addIfInBoundary(index, index));
        }
        availableSquares.remove(square);
        return availableSquares;
    }
}
