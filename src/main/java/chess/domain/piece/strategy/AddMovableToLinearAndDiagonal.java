package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.board.Square;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;

import static chess.domain.piece.Piece.FIRST_SHIFT;
import static chess.domain.piece.Piece.LAST_SHIFT;

public class AddMovableToLinearAndDiagonal implements AddMovable{

    @Override
    public void addMovable(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction) {
        Square centerSquare = availableSquares.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        for (int moveTime = FIRST_SHIFT; moveTime < LAST_SHIFT; moveTime++) {
            Square squareToAdd = Square.moveTo(
                    direction.getFileDegree() * moveTime, direction.getRankDegree() * moveTime, centerSquare
            );
            availableSquares.add(squareToAdd);
            if (chessBoard.containsKey(squareToAdd) && !squareToAdd.equals(centerSquare)) {
                chessBoard.get(centerSquare).removeSquareWhenSameColor(chessBoard, availableSquares, squareToAdd);
                break;
            }
        }
    }
}
