package chess.domain2.piece;

import chess.domain2.Color;
import chess.domain2.Direction;
import chess.domain2.Square;
import chess.domain2.Type;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Rook extends Piece {
    private final static Map<String, Rook> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), new Rook(color));
        }
    }

    private final Type type = Type.ROOK;

    public Rook(Color color) {
        super(color);
    }

    public static Rook of(Color color) {
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
        for (Direction direction : Direction.linearDirection()) {
            addMovableSquares(chessBoard, availableSquares, direction);
        }
        availableSquares.remove(pieceSquare);
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
}
