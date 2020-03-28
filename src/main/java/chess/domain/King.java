package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class King extends Piece {

    private final static Map<String, King> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), new King(color));
        }
    }

    private final Type type = Type.KING;

    public King(Color color) {
        super(color);
    }

    public static King of(Color color) {
        validateInput(color);
        return CACHE.get(color.getName());
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    @Override
    public void removeSquareIfSameColor(Map<Square, Piece> board, Set<Square> squares, Square pawnScopeSquare) {
        if (board.containsKey(pawnScopeSquare) && color == board.get(pawnScopeSquare).color) {
            squares.remove(pawnScopeSquare);
        }
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        int index = -1;
        for (int i = 0; i < 2; i++) {
            availableSquares.add(getMovedSquareByFileAndRank(square, index, 0));
            availableSquares.add(getMovedSquareByFileAndRank(square, 0, index));
            availableSquares.add(getMovedSquareByFileAndRank(square, index * -1, index));
            availableSquares.add(getMovedSquareByFileAndRank(square, index, index));
            index *= -1;
        }
        return availableSquares;
    }

    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        return calculateScope(square).stream()
                .filter(knightScopeSquare ->
                        !(board.containsKey(knightScopeSquare) && board.get(knightScopeSquare).color == color))
                .collect(Collectors.toSet());
    }

    @Override
    public double getScore() {
        return type.getScore();
    }

    @Override
    public String getLetter() {
        if (color == Color.BLACK) {
            return type.getName();
        }
        return type.getName().toLowerCase();
    }
}
