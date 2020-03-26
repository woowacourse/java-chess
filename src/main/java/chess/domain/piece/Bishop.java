package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bishop extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        Type type = Type.BISHOP;
        for (Color color : Color.values()) {
            CACHE.put(color, new Bishop(color, type));
        }
    }

    public Bishop(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<Square> getAllCheatSheet(Square square) {
        NullChecker.validateNotNull(square);
        Set<Square> availableSquares = new HashSet<>();
        for (int index = -7; index < 8; index++) {
            availableSquares.add(square.addIfInBoundary(index * -1, index));
            availableSquares.add(square.addIfInBoundary(index, index));
        }
        availableSquares.remove(square);
        return availableSquares;
    }

    @Override
    public Set<Square> getCheatSheet(Square square, Map<Square, Piece> board) {
        NullChecker.validateNotNull(square, board);
        Set<Square> squares = getAllCheatSheet(square);
        Set<Square> squaresIter = getAllCheatSheet(square);
        for (Square s : squaresIter) {
            if (board.containsKey(s)) {
                int fileDifference = s.getFile() - square.getFile();
                int rankDifference = s.getRank() - square.getRank();

                if (fileDifference > 0 && rankDifference > 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, 1, 1);
                    squares.removeAll(squaresToRemove);
                }

                if (fileDifference > 0 && rankDifference < 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, 1, -1);
                    squares.removeAll(squaresToRemove);
                }

                if (fileDifference < 0 && rankDifference > 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, -1, 1);
                    squares.removeAll(squaresToRemove);
                }

                if (fileDifference < 0 && rankDifference < 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, -1, -1);
                    squares.removeAll(squaresToRemove);
                }

                if (isSameColor(board.get(s))) {
                    squares.remove(s);
                }
            }
        }
        return squares;

    }
}
