package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Square;
import chess.domain.Type;

import java.util.*;
import java.util.stream.Collectors;

public class PieceImpl extends Piece {
    private final static Map<String, PieceImpl> CACHE = new HashMap<>();

    public Type type;

    public PieceImpl(Color color, Type type, String name, double score) {
        super(color, type, name, score);
    }


    public static PieceImpl of(Color color, Type type) {
        validateInput(color, type);
        return CACHE.get(color.getName() + type.getName());
    }

    private static void validateInput(Color color, Type type) {
        if (Objects.isNull(color) || Objects.isNull(type)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    public String getName() {
        if (color == Color.BLACK) {
            return type.getName();
        }
        return type.getName().toLowerCase();
    }

    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();

        if (type.equals(Type.QUEEN)) {
            for (int index = -7; index < 8; index++) {
                availableSquares.add(addIfInBoundary(square, index, 0));
                availableSquares.add(addIfInBoundary(square, 0, index));
                availableSquares.add(addIfInBoundary(square, index * -1, index));
                availableSquares.add(addIfInBoundary(square, index, index));
            }
            availableSquares.remove(square);
            return availableSquares;
        }

        if (type.equals(Type.BISHOP)) {
            for (int index = -7; index < 8; index++) {
                availableSquares.add(addIfInBoundary(square, index * -1, index));
                availableSquares.add(addIfInBoundary(square, index, index));
            }
            availableSquares.remove(square);
            return availableSquares;
        }

        if (type.equals(Type.ROOK)) {
            for (int index = -7; index < 8; index++) {
                availableSquares.add(addIfInBoundary(square, index, 0));
                availableSquares.add(addIfInBoundary(square, 0, index));
            }
            availableSquares.remove(square);
            return availableSquares;
        }

        if (type.equals(Type.KING)) {
            int index = -1;
            for (int i = 0; i < 2; i++) {
                availableSquares.add(addIfInBoundary(square, index, 0));
                availableSquares.add(addIfInBoundary(square, 0, index));
                availableSquares.add(addIfInBoundary(square, index * -1, index));
                availableSquares.add(addIfInBoundary(square, index, index));
                index *= -1;
            }
            return availableSquares;
        }

        if (type.equals(Type.KNIGHT)) {
            int x = -1;
            int y = 2;
            for (int i = 0; i < 2; i++) {
                availableSquares.add(addIfInBoundary(square, x, y));
                availableSquares.add(addIfInBoundary(square, y, x));
                availableSquares.add(addIfInBoundary(square, x, (-1) * y));
                availableSquares.add(addIfInBoundary(square, y * -1, x));
                x *= -1;
                y *= -1;
            }
            return availableSquares;
        }

        if (type.equals(Type.PAWN)) {
            int index = 1;
            if (color.equals(Color.BLACK)) {
                index *= -1;
            }
            if ((color.equals(Color.BLACK) && square.getRank() == 7) ||
                    (color.equals(Color.WHITE) && square.getRank() == 2)) {
                availableSquares.add(addIfInBoundary(square, 0, index * 2));
            }
            availableSquares.add(addIfInBoundary(square, 0, index));
            return availableSquares;
        }

        throw new IllegalArgumentException("올바른 타입이 아닙니다");
    }


    public Square addIfInBoundary(Square square, int fileIncrementBy, int rankIncrementBy) {
        if (Square.hasCacheAdded(square, fileIncrementBy, rankIncrementBy)) {
            return Square.of(square, fileIncrementBy, rankIncrementBy);
        }
        return square;
    }

    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        if (type == Type.KNIGHT || type == Type.KING) {
            return calculateScope(square).stream()
                    .filter(s -> !(board.containsKey(s) && board.get(s).color == color))
                    .collect(Collectors.toSet());
        }

        if (type == Type.PAWN) {
            Set<Square> squares = calculateScope(square);
            for (Square s : squares) {
                if (Math.abs(square.getRank() - s.getRank()) == 1) {
                    Square squareRight = s;
                    Square squareLeft = s;
                    if (s.getFile() != 'a') {
                        squareLeft = Square.of(s, -1, 0);
                    }
                    if (s.getFile() != 'h') {
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

        if (type == Type.QUEEN) {
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

        if (type == Type.BISHOP) {
            Set<Square> squares = calculateScope(square);
            Set<Square> squaresIter = calculateScope(square);
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

                    if (board.get(s).color == color) {
                        squares.remove(s);
                    }
                }
            }
            return squares;
        }

        if (type == Type.ROOK) {
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

        throw new IllegalArgumentException("no type");
    }

    public Set<Square> findSquaresToRemove(Square s, int fileAddAmount, int rankAddAmount) {
        Set<Square> squaresToRemove = new HashSet<>();
        int file = 0;
        int rank = 0;
        for (int i = 0; i < 8; i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(addIfInBoundary(s, file, rank));
        }
        squaresToRemove.remove(s);
        return squaresToRemove;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public double getScore() {
        return type.getScore();
    }

}