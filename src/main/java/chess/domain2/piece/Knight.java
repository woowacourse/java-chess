package chess.domain2.piece;

import chess.domain2.Color;
import chess.domain2.Direction;
import chess.domain2.Square;
import chess.domain2.Type;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Knight extends Piece {
    private final static Map<String, Knight> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), new Knight(color));
        }
    }

    private final Type type = Type.KNIGHT;

    public Knight(Color color) {
        super(color);
    }

    public static Knight of(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        return CACHE.get(color.getName());
    }

    @Override
    public Set<Square> getMovableSquares(Square centerSquare, Map<Square, Piece> chessBoard) {
        validate(centerSquare, chessBoard);
        Set<Square> availableSquares = new LinkedHashSet<>();
        availableSquares.add(centerSquare);
        for (Direction direction : Direction.knightDirection()) {
            addMovableSquares(chessBoard, availableSquares, direction);
        }
        availableSquares.remove(centerSquare);
        return availableSquares;
    }

    @Override
    void addMovableSquares(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction) {
        Square centerSquare = availableSquares.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        Square squareToAdd = Square.moveTo(
                direction.getFileDegree(), direction.getRankDegree(), centerSquare);
        availableSquares.add(squareToAdd);
        if (!squareToAdd.equals(centerSquare)) {
            removeSameColorSquare(chessBoard, availableSquares, squareToAdd);
        }
    }

    @Override
    public String getLetter() {
        if (color == Color.BLACK) {
            return type.getName();
        }
        return type.getName().toLowerCase();
    }

    @Override
    public double getScore() {
        return type.getScore();
    }
}
