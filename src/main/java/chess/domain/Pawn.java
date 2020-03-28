package chess.domain;

import java.util.*;

public class Pawn extends Piece {

    private final static Map<String, Pawn> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), new Pawn(color));
        }
    }

    private final Type type = Type.PAWN;


    public Pawn(Color color) {
        super(color);
    }

    public static Pawn of(Color color) {
        validateInput(color);
        return CACHE.get(color.getName());
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Set<Square> availableSquares = new HashSet<>();
        int rank = getRankLevelByColor();
        if ((color.equals(Color.BLACK) && square.getRank() == 7) ||
                (color.equals(Color.WHITE) && square.getRank() == 2)) {
            availableSquares.add(getMovedSquareByFileAndRank(square, 0, rank * 2));
        }
        availableSquares.add(getMovedSquareByFileAndRank(square, 0, rank));
        return availableSquares;
    }

    private int getRankLevelByColor() {
        int rank = 1;
        if (color.equals(Color.BLACK)) {
            rank *= -1;
        }
        return rank;
    }


    //Todo 이하 모든 메서드 리팩토링
    @Override
    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        validateNotNull(square, board);
        Set<Square> pawnScopeSquares = calculateScope(square);
        for (Square pawnScopeSquare : pawnScopeSquares) {
            if (Math.abs(square.getRank() - pawnScopeSquare.getRank()) == 1) {
                if (board.containsKey(pawnScopeSquare) && color == board.get(pawnScopeSquare).color) {
                    pawnScopeSquares.removeAll(calculateScope(square));
                }
                captureWhenColorDifferent(board, pawnScopeSquares, pawnScopeSquare);
                continue;
            }
            removeSquareIfSameColor(board, pawnScopeSquares, pawnScopeSquare);
        }
        return pawnScopeSquares;
    }

    private void captureWhenColorDifferent(Map<Square, Piece> board, Set<Square> pawnScopeSquares, Square pawnScopeSquare) {
        Square squareRight = pawnScopeSquare;
        Square squareLeft = pawnScopeSquare;

        if (pawnScopeSquare.getFile() != 'a') {
            squareLeft = Square.of(pawnScopeSquare, -1, 0);
        }
        if (pawnScopeSquare.getFile() != 'h') {
            squareRight = Square.of(pawnScopeSquare, 1, 0);
        }
        if (board.containsKey(squareRight) && color != board.get(squareRight).color) {
            pawnScopeSquares.add(squareRight);
        }
        if (board.containsKey(squareLeft) && color != board.get(squareLeft).color) {
            pawnScopeSquares.add(squareLeft);
        }
    }

    @Override
    public void removeSquareIfSameColor(Map<Square, Piece> board, Set<Square> squares, Square pawnScopeSquare) {
        if (board.containsKey(pawnScopeSquare) && color == board.get(pawnScopeSquare).color) {
            squares.remove(pawnScopeSquare);
        }
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
