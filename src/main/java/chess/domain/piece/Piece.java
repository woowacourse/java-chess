package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Square;
import chess.domain.piece.strategy.AddMovable;

import java.util.Map;
import java.util.Set;

public abstract class Piece implements AddMovable {

    public static final int FIRST_SHIFT = 1;
    public static final int LAST_SHIFT = 8;
    private static final String INPUT_ERROR_MESSAGE = "잘못된 입력입니다.";
    private static final String NULL_INPUT_ERROR_MESSAGE = "입력이 존재하지 않습니다.";

    protected final Color color;
    protected AddMovable addMovable;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract Set<Square> findMovable(Square pieceSquare, Map<Square, Piece> chessBoard);

    public void addMovable(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction){
        addMovable.addMovable(chessBoard,availableSquares,direction);
    }

    static void checkColorNull(Color color) {
        if (color == null) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
    }

    public void removeSameColorSquare(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Square squareToAdd) {
        if (chessBoard.containsKey(squareToAdd)) {
            removeSquareWhenSameColor(chessBoard, availableSquares, squareToAdd);
        }
    }

    public void removeSquareWhenSameColor(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Square squareToAdd) {
        if (!(chessBoard.get(squareToAdd) == null) && chessBoard.get(squareToAdd).color.equals(color)) {
            availableSquares.remove(squareToAdd);
        }
    }

    void validate(Square pieceSquare, Map<Square, Piece> chessBoard) {
        if (pieceSquare == null || chessBoard == null) {
            throw new IllegalArgumentException(NULL_INPUT_ERROR_MESSAGE);
        }
    }

    public Color getColor() {
        return color;
    }

    public abstract String getLetter();

    public abstract double getScore();

    public abstract String toString();

}
