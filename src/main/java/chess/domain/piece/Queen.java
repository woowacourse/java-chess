package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Square;
import chess.domain.piece.strategy.AddMovableToLinearAndDiagonal;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Queen extends Piece {
    private final static Map<String, Queen> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), new Queen(color));
        }
    }

    private final Type type = Type.QUEEN;

    public Queen(Color color) {
        super(color);
        addMovable = new AddMovableToLinearAndDiagonal();
    }

    public static Queen of(Color color) {
        checkColorNull(color);
        return CACHE.get(color.getName());
    }

    @Override
    public Set<Square> findMovable(Square centerSquare, Map<Square, Piece> chessBoard) {
        validate(centerSquare, chessBoard);
        Set<Square> availableSquares = new LinkedHashSet<>();
        availableSquares.add(centerSquare);
        for (Direction direction : Direction.linearAndDiagonalDirection()) {
            addMovable(chessBoard, availableSquares, direction);
        }
        availableSquares.remove(centerSquare);
        return availableSquares;
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

    @Override
    public String toString() {
        return color.getName()+type.getFullName();
    }
}
