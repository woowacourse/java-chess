package chess.domain2.piece;

import chess.domain2.Color;
import chess.domain2.Direction;
import chess.domain2.Square;

import java.util.Map;
import java.util.Set;

public abstract class Piece {

    static final int FIRST_SHIFT = 1;
    static final int LAST_SHIFT = 8;

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract Set<Square> getMovableSquares(Square pieceSquare, Map<Square, Piece> chessBoard);

    public abstract String getLetter();

    abstract void addMovableSquares(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction);

    void validate(Square pieceSquare, Map<Square, Piece> chessBoard) {
        if (pieceSquare == null || chessBoard == null) {
            throw new IllegalArgumentException("입력이 존재하지 않습니다.");
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

    public Color getColor() {
        return color;
    }
}
