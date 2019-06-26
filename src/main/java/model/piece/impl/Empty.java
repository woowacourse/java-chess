package model.piece.impl;

import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.Collections;
import java.util.List;

public class Empty extends Piece {
    public Empty(Position position) {
        super(PieceColor.EMPTY, position);
    }

    public Empty(PieceColor pieceColor, Position position) {
        super(PieceColor.EMPTY, position);
    }

    @Override
    public List<Position> getMovablePositions(BoardView board) {
        return Collections.emptyList();
    }

    @Override
    protected boolean isMovableTo(Position position, BoardView boardView) {
        return false;
    }

    @Override
    public boolean isEnemyOf(PieceColor other) {
        return false;
    }
}
