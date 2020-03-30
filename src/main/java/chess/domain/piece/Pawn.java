package chess.domain.piece;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.*;
import java.util.stream.Stream;

public class Pawn extends Piece {
    private final static Map<Color, Pawn> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "P";
    private final static String NAME_WHITE = "p";
    private final static double SCORE = 1;

    static {
        Stream.of(Color.values())
                .forEach(Pawn::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = putNameByColor(color);
        CACHE.putIfAbsent(color, new Pawn(color, name, SCORE));
    }

    private static String putNameByColor(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        return name;
    }

    public static Pawn of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Pawn(Color color, String name, double score) {
        super(color, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        int index = 1;
        if (color.equals(Color.BLACK)) {
            index *= -1;
        }
        if ((color.equals(Color.BLACK) && square.getRank() == Rank.of(7)) ||
                (color.equals(Color.WHITE) && square.getRank() == Rank.of(2))) {
            availableSquares.add(square.movedSquareInBoundary(0, index * 2));
        }
        availableSquares.add(square.movedSquareInBoundary(0, index));
        return availableSquares;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        Set<Square> squares = calculateScope(square);
        Rank currentRank = square.getRank();
        for (Square s : squares) {
            Rank movedRank = s.getRank();
            if (Math.abs(currentRank.getNumber() - movedRank.getNumber()) == 1) {
                Square squareRight = s;
                Square squareLeft = s;
                if (s.getFile() != File.of('a')) {
                    squareLeft = Square.of(s, -1, 0);
                }
                if (s.getFile() != File.of('h')) {
                    squareRight = Square.of(s, 1, 0);
                }
                if (board.containsKey(s) && color == board.get(s).color) {
                    squares.removeAll(calculateScope(square));
                }
                if (board.containsKey(squareRight) && color != board.get(squareRight).color) {
                    squares.add(squareRight);
                }
                if (board.containsKey(squareLeft) && color != board.get(squareLeft).color) {
                    squares.add(squareLeft);
                }
                continue;
            }
            if (board.containsKey(s) && color == board.get(s).color) {
                squares.remove(s);
            }
        }
        return squares;
    }
}
