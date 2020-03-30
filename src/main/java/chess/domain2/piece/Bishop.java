package chess.domain2.piece;

import chess.domain2.Color;
import chess.domain2.Direction;
import chess.domain2.Square;
import chess.domain2.Type;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Bishop extends Piece {
    private final static Map<String, Bishop> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), new Bishop(color));
        }
    }

    private final Type type = Type.BISHOP;

    public Bishop(Color color) {
        super(color);
    }

    public static Bishop of(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        return CACHE.get(color.getName());
    }

    @Override
    public Set<Square> getMovableSquares(Square pieceSquare, Map<Square, Piece> chessBoard) {
        validate(pieceSquare, chessBoard);
        Set<Square> availableSquares = new LinkedHashSet<>();
        availableSquares.add(pieceSquare);
        for (Direction direction : Direction.diagonalDirection()) {
            addMovableSquares(chessBoard, availableSquares, direction);
        }
        availableSquares.remove(pieceSquare);
        return availableSquares;
    }

    @Override
    void addMovableSquares(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction) {
        Square centerSquare = availableSquares.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        for (int moveTime = 1; moveTime < 8; moveTime++) {
            Square squareToAdd = Square.moveTo(
                    direction.getFileDegree() * moveTime, direction.getRankDegree() * moveTime, centerSquare
            );
            availableSquares.add(squareToAdd);
            if (chessBoard.containsKey(squareToAdd)) {
                removeSquareWhenSameColor(chessBoard, availableSquares, squareToAdd);
                break;
            }
        }
    }

    private void removeSquareWhenSameColor(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Square add) {
        if (chessBoard.get(add).color.equals(color)) {
            availableSquares.remove(add);
        }
    }

    @Override
    public String getLetter() {
        if (color == Color.BLACK) {
            return type.getName();
        }
        return type.getName().toLowerCase();
    }
}
