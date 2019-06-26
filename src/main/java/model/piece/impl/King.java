package model.piece.impl;

import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.List;

public class King extends Piece {
    public King(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public List<Position> getMovablePositions(BoardView board) {
        return null;
    }
}
