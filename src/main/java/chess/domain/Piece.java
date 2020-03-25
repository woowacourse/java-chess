package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Piece {
    private final static Map<String, Piece> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            for (Type type : Type.values()) {
                CACHE.put(color.getName() + type.getName(), new Piece(color, type));
            }
        }
    }

    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece of(Color color, Type type) {
        validateInput(color, type);
        return CACHE.get(color.getName() + type.getName());
    }

    private static void validateInput(Color color, Type type) {
        if (Objects.isNull(color) || Objects.isNull(type)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    public String getLetter() {
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


    private Square addIfInBoundary(Square square, int fileIncrementBy, int rankIncrementBy) {
        if (Square.hasCacheAdded(square, fileIncrementBy, rankIncrementBy)) {
            return Square.of(square, fileIncrementBy, rankIncrementBy);
        }
        return square;
    }

    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        // Todo 색비교 메서드로 분리 요청
        if (type == Type.KNIGHT) {
            return calculateScope(square).stream()
                    .filter(s -> !(board.containsKey(s) && board.get(s).color == color))
                    .collect(Collectors.toSet());
        }

        if (type == Type.PAWN) {
            Set<Square> squares = calculateScope(square);
            for (Square s : squares) {
                if (Math.abs(square.getRank() - s.getRank()) == 1) {
                    Square squareRight = Square.of(s, 1, 0);
                    Square squareLeft = Square.of(s, -1, 0);
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
        throw new IllegalArgumentException("no type");
    }

    private void validateNotNull(Square square, Map<Square, Piece> board) {
        if (Objects.isNull(square) || Objects.isNull(board)) {
            throw new IllegalArgumentException("null 안댐");
        }
    }
    // Todo 칸-말 맵 받아서 자기가 움직일 수 있는 리스트 보내줌


}