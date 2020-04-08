package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.Square;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;

public class AddMovableWhenKing implements AddMovable {
    @Override
    public void addMovable(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction) {
        Square centerSquare = availableSquares.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        Square squareToAdd = Square.moveTo(
                direction.getFileDegree(), direction.getRankDegree(), centerSquare);
        availableSquares.add(squareToAdd);
        if (chessBoard.containsKey(squareToAdd) && !squareToAdd.equals(centerSquare)) {
            chessBoard.get(centerSquare).removeSquareWhenSameColor(chessBoard, availableSquares, squareToAdd);
        }
    }
}
