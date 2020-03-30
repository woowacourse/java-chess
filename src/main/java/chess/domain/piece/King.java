package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.Type;
import chess.domain.board.Square;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
        checkColorNull(color);
        return CACHE.get(color.getName());
    }

    @Override
    public Set<Square> getMovableSquares(Square centerSquare, Map<Square, Piece> chessBoard) {
        validate(centerSquare, chessBoard);
        Set<Square> availableSquares = new LinkedHashSet<>();
        availableSquares.add(centerSquare);
        for (Direction direction : Direction.linearAndDiagonalDirection()) {
            addMovableSquares(chessBoard, availableSquares, direction);
        }
        return availableSquares;
    }

    @Override
    void addMovableSquares(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction) {
        Square centerSquare = availableSquares.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        Square squareToAdd = Square.moveTo(
                direction.getFileDegree(), direction.getRankDegree(), centerSquare);
        availableSquares.add(squareToAdd);
        if (chessBoard.containsKey(squareToAdd) && !squareToAdd.equals(centerSquare)) {
            removeSquareWhenSameColor(chessBoard, availableSquares, squareToAdd);
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
