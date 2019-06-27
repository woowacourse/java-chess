package model.piece.impl;

import model.Direction;
import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.List;
import java.util.stream.Collectors;

import static model.piece.PieceColor.BLACK;

public class King extends Piece {
    private static final double SCORE = 0;

    public King(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public List<Position> getMovablePositions(BoardView boardView) {
        List<Direction> possibleDirections = Direction.ofAll();

        return possibleDirections.stream()
                .filter(direction -> position.of(direction).isValid()
                        && isMovableTo(position.of(direction), boardView))
                .map(direction -> position.of(direction))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return (pieceColor == BLACK) ? "♚" : "♔";
    }
}
