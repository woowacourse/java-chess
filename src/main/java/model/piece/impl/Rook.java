package model.piece.impl;

import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public List<Position> getMovablePositions(BoardView board) {
        return null;
    }
}
