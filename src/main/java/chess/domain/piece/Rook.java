package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Rook extends Piece {
    private final static Map<Color, Rook> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "R";
    private final static String NAME_WHITE = "r";
    private final static double SCORE = 5;

    private Rook(Color color, String name, double score) {
        super(color, name, score);
    }

    static {
        Stream.of(Color.values())
                .forEach(Rook::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = putNameByColor(color);
        CACHE.putIfAbsent(color, new Rook(color, name, SCORE));
    }

    private static String putNameByColor(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        return name;
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

    @Override
    public Set<Square> calculateScope(Square square) {
        Objects.requireNonNull(square, "square은 필수입니다");
        Set<Square> scope = new HashSet<>();
        IntStream.rangeClosed(1, 8)
                .forEach(count -> scope.addAll(Direction.linearDirection()
                        .stream()
                        .map(direction -> square.movedSquareInBoundary(direction, count))
                        .filter(movedSquare -> isNotSameSquareItself(square, movedSquare))
                        .collect(Collectors.toSet())));
        return scope;
    }

    @Override
    public Set<Square> calculateMovableSquares(Square currentSquare, Map<Square, Piece> board) {
        validateNotNull(currentSquare, board);
        Set<Square> squares = calculateScope(currentSquare);
        Set<Square> squaresIter = calculateScope(currentSquare);
        File currentFile = currentSquare.getFile();
        Rank currentRank = currentSquare.getRank();
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

                if (board.get(s).color == color) {
                    squares.remove(s);
                }
            }
        }
        return squares;
    }
}
