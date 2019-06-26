package model.piece.impl;

import model.Direction;
import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;

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

        return movablePositions;
    }

    @Override
    protected boolean isMovableTo(Position position, BoardView boardView) {
        return position.isValid() && boardView.getPieceColorAt(position) == PieceColor.EMPTY;
    }

    private boolean isAttackableTo(Position position, BoardView boardView) {
        return position.isValid() && isEnemyOf(boardView.getPieceColorAt(position));
    }
}
