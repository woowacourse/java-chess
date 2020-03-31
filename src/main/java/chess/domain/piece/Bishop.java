package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Bishop extends Piece {
    private final static Map<Color, Bishop> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "B";
    private final static String NAME_WHITE = "b";
    private final static double SCORE = 3;

    static {
        Stream.of(Color.values())
                .forEach(Bishop::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = putNameByColor(color);
        CACHE.putIfAbsent(color, new Bishop(color, name, SCORE));
    }

    private static String putNameByColor(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        return name;
    }

    public static Bishop of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Bishop(Color color, String name, double score) {
        super(color, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Objects.requireNonNull(square, "square은 필수입니다");
        Set<Square> scope = new HashSet<>();
        IntStream.rangeClosed(1, 8)
                .forEach(count -> scope.addAll(Direction.diagonalDirection()
                        .stream()
                        .map(direction -> square.movedSquareInBoundary(direction, count))
                        .filter(movedSquare -> isNotSameSquareItself(square, movedSquare))
                        .collect(Collectors.toSet())));
        return scope;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        Set<Square> squares = calculateScope(square);
        Set<Square> squaresIter = calculateScope(square);
        File currentFile = square.getFile();
        Rank currentRank = square.getRank();
        for (Square s : squaresIter) {
            File movedFile = s.getFile();
            Rank movedRank = s.getRank();
            if (board.containsKey(s)) {
                int fileDifference = movedFile.getNumber() - currentFile.getNumber();
                int rankDifference = movedRank.getNumber() - currentRank.getNumber();

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
