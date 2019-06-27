package model.piece.impl;

import model.Direction;
import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;

import static model.piece.PieceColor.BLACK;

public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public void moveTo(Position position) {
        hasMoved = true;
        super.moveTo(position);
    }

    @Override
    public List<Position> getMovablePositions(BoardView boardView) {
        List<Position> movablePositions = new ArrayList<>();
        Direction forwardTo = pieceColor == PieceColor.WHITE ? Direction.NORTH : Direction.SOUTH;
        if (isMovableTo(position.of(forwardTo), boardView)) {
            movablePositions.add(position.of(forwardTo));
            if (!hasMoved && isMovableTo(position.of(forwardTo).of(forwardTo), boardView)) {
                movablePositions.add(position.of(forwardTo).of(forwardTo));
            }
        }

        if (isAttackableTo(position.of(forwardTo.clockwiseNext()), boardView)) {
            movablePositions.add(position.of(forwardTo.clockwiseNext()));
        }

        if (isAttackableTo(position.of(forwardTo.counterclockwiseNext()), boardView)) {
            movablePositions.add(position.of(forwardTo.counterclockwiseNext()));
        }

        return movablePositions;
    }

    @Override
    protected boolean isMovableTo(Position position, BoardView boardView) {
        return position.isValid() && boardView.getPieceColorAt(position) == PieceColor.EMPTY;
    }

    private boolean isAttackableTo(Position position, BoardView boardView) {
        return position.isValid() && isEnemyOf(boardView.getPieceColorAt(position));
    }

    @Override
    public String toString() {
        return (pieceColor == BLACK) ? "♟" : "♙";
    }
}
