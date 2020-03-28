package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Square;

import java.util.*;
import java.util.stream.Stream;

public class Rook extends Piece {
    private final static Map<Color, Rook> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "R";
    private final static String NAME_WHITE = "r";
    private final static double SCORE = 5;

    static {
        Stream.of(Color.values())
                .forEach(Rook::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        CACHE.putIfAbsent(color, new Rook(color, name, SCORE));
    }

    public static Rook of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Rook(Color color, String name, double score) {
        super(color, null, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        for (int index = -7; index < 8; index++) {
            availableSquares.add(addIfInBoundary(square, index, 0));
            availableSquares.add(addIfInBoundary(square, 0, index));
        }
        availableSquares.remove(square);
        return availableSquares;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        Set<Square> squares = calculateScope(square);
        Set<Square> squaresIter = calculateScope(square);
        for (Square s : squaresIter) {
            if (board.containsKey(s)) {
                int fileDifference = s.getFile() - square.getFile();
                int rankDifference = s.getRank() - square.getRank();
                if (fileDifference == 0 && rankDifference > 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, 0, 1);
                    squares.removeAll(squaresToRemove);
                }

                if (fileDifference > 0 && rankDifference == 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, 1, 0);
                    squares.removeAll(squaresToRemove);
                }

                if (fileDifference < 0 && rankDifference == 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, -1, 0);
                    squares.removeAll(squaresToRemove);
                }

                if (fileDifference == 0 && rankDifference < 0) {
                    Set<Square> squaresToRemove = findSquaresToRemove(s, 0, -1);
                    squares.removeAll(squaresToRemove);
                }

                if (board.get(s).color == color) {
                    squares.remove(s);
                }
            }
        }
        return squares;
    }
}
