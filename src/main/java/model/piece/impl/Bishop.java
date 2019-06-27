package model.piece.impl;

import model.Direction;
import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;

import static model.piece.PieceColor.BLACK;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public List<Position> getMovablePositions(BoardView boardView) {
        List<Direction> possibleDirections = Direction.ofDiagonal();
        List<Position> movablePositions = new ArrayList<>();
        Position checkingPosition;

        for (Direction direction : possibleDirections) {
            checkingPosition = position.of(direction);
            movablePositions.addAll(findAllMovablePositionsToDirection(boardView, checkingPosition, direction));
        }

        return movablePositions;
    }

    private List<Position> findAllMovablePositionsToDirection(BoardView boardView, Position checkingPosition, Direction direction) {
        List<Position> movablePositions = new ArrayList<>();

        while (checkingPosition.isValid()
                && isMovableTo(checkingPosition, boardView)) {
            movablePositions.add(checkingPosition);

            if (isEnemyOf(boardView.getPieceColorAt(checkingPosition))) {
                break;
            }
            checkingPosition = checkingPosition.of(direction);
        }
        return movablePositions;
    }

    @Override
    public String toString() {
        return (pieceColor == BLACK) ? "♝" : "♗";
    }
}
