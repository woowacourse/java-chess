package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.board.Square;
import chess.domain.piece.strategy.AddMovableToLinearAndDiagonal;

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
        addMovable = new AddMovableToLinearAndDiagonal();
    }

    public static Bishop of(Color color) {
        checkColorNull(color);
        return CACHE.get(color.getName());
    }

    @Override
    public Set<Square> findMovable(Square pieceSquare, Map<Square, Piece> chessBoard) {
        validate(pieceSquare, chessBoard);
        Set<Square> availableSquares = new LinkedHashSet<>();
        availableSquares.add(pieceSquare);
        for (Direction direction : Direction.diagonalDirection()) {
            addMovable(chessBoard, availableSquares, direction);
        }
        availableSquares.remove(pieceSquare);
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

}
