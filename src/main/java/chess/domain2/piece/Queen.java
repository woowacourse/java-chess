package chess.domain2.piece;

import chess.domain2.Color;
import chess.domain2.Direction;
import chess.domain2.Square;
import chess.domain2.Type;

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
    }

    public static Queen of(Color color) {
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
        for (Direction direction : Direction.linearAndDiagonalDirection()) {
            addMovableSquares(chessBoard, availableSquares, direction);
        }
        availableSquares.remove(centerSquare);
        return availableSquares;
    }

    @Override
    void addMovableSquares(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction) {
        Square centerSquare = availableSquares.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        for (int moveTime = FIRST_SHIFT; moveTime < LAST_SHIFT; moveTime++) {
            Square squareToAdd = Square.moveTo(
                    direction.getFileDegree() * moveTime, direction.getRankDegree() * moveTime, centerSquare
            );
            availableSquares.add(squareToAdd);
            if (chessBoard.containsKey(squareToAdd) && !squareToAdd.equals(centerSquare)) {
                removeSquareWhenSameColor(chessBoard, availableSquares, squareToAdd);
                break;
            }
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
