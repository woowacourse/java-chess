package chess.domain.piece;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.*;
import java.util.stream.Stream;

public class Queen extends Piece {
    private final static Map<Color, Queen> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "Q";
    private final static String NAME_WHITE = "q";
    private final static double SCORE = 9;

    static {
        Stream.of(Color.values())
                .forEach(Queen::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = putNameByColor(color);
        CACHE.putIfAbsent(color, new Queen(color, name, SCORE));
    }

    private static String putNameByColor(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        return name;
    }

    public static Queen of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Queen(Color color, String name, double score) {
        super(color, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();

        for (int index = -7; index < 8; index++) {
            availableSquares.add(square.movedSquareInBoundary(index, 0));
            availableSquares.add(square.movedSquareInBoundary(0, index));
            availableSquares.add(square.movedSquareInBoundary(index * -1, index));
            availableSquares.add(square.movedSquareInBoundary(index, index));
        }
        availableSquares.remove(square);
        return availableSquares;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        Set<Square> squares = calculateScope(square);
        Set<Square> squaresIter = calculateScope(square);
        File currentFile = square.getFile();
        Rank currentRank = square.getRank();
        for (Square s : squaresIter) {
            if (board.containsKey(s)) {
                File movedFile = s.getFile();
                Rank movedRank = s.getRank();
                int fileDifference = movedFile.getNumber() - currentFile.getNumber();
                int rankDifference = movedRank.getNumber() - currentRank.getNumber();
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

                if (board.get(s).color == color) {
                    squares.remove(s);
                }
            }
        }
        return squares;
    }
}
