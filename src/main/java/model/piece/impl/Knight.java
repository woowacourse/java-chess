package model.piece.impl;

import model.Direction;
import model.Position;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.Direction.*;
import static model.piece.PieceColor.BLACK;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    public Knight(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public List<Position> getMovablePositions(BoardView boardView) {
        List<Position> candidatePositions = getAllCandidatesOfPosition();

        return candidatePositions.stream()
                .filter(Position::isValid)
                .filter(position -> isMovableTo(position, boardView))
                .collect(Collectors.toList());
    }

    private List<Position> getAllCandidatesOfPosition() {
        return Arrays.asList(
                position.of(NORTH).of(NORTH_WEST),
                position.of(NORTH).of(NORTH_EAST),
                position.of(EAST).of(NORTH_EAST),
                position.of(EAST).of(SOUTH_EAST),
                position.of(SOUTH).of(SOUTH_EAST),
                position.of(SOUTH).of(SOUTH_WEST),
                position.of(WEST).of(SOUTH_WEST),
                position.of(WEST).of(NORTH_WEST)
        );
    }

    @Override
    public String toString() {
        return (pieceColor == BLACK) ? "♞" : "♘";
    }
}
