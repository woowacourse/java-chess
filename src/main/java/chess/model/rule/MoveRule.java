package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class MoveRule {
    private Map<UnitClass, MoveRule> map = new HashMap<>();

    protected Piece piece;

    MoveRule(final Piece piece) {
        this.piece = piece;
    }

    abstract List<Square> getMovableSquares(final Board board, final Square square);

    private List<Square> getOccupiedSquares(final Board board, final List<Square> squaresCandidate, final Piece piece) {
        return squaresCandidate.stream()
                .filter(square -> getPiece(board, square) != null)
                .filter(square -> getPiece(board, square).getSide() == piece.getSide())
                .collect(Collectors.toList());
    }

    private Piece getPiece(final Board board, final Square square) {
        try {
            return board.getPiece(square);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    List<Square> getNonBlockedNeighbors(final Board board, final Square square, final Function<Square, Square> getNeighbors) {
        final List<Square> squareList = new ArrayList<>();
        Square nextSquare = getNeighbors.apply(square);
        while (getPiece(board, nextSquare) == null) {
            squareList.add(nextSquare);
        }
        return squareList;
    }

    //    public abstract boolean isValidMove(final Board board, final Square checkTarget, final Square destination);
}
