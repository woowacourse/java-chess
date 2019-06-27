package chess.model;

import chess.model.board.Board;
import chess.model.unit.Pawn;
import chess.model.unit.Piece;
import chess.model.unit.Side;
import chess.model.unit.UnitType;

import java.util.ArrayList;
import java.util.List;

public class MoveHandler {
    private Board board;

    MoveHandler(Board board) {
        this.board = board;
    }

    public boolean move(Square beginSquare, Square endSquare, Side side) {
        if (isNullPiece(beginSquare) || !isSameSide(beginSquare, side) || isEqualSquare(beginSquare, endSquare))
            return false;
        List<Square> squares = findPossibleSquares(beginSquare, isNullPiece(endSquare));
        return squares.contains(endSquare);
    }

    private boolean isNullPiece(Square square) {
        return board.isNullPiece(square);
    }

    private boolean isSameSide(Square beginSquare, Side side) {
        return board.getPiece(beginSquare).isSameSide(side);
    }

    private boolean isEqualSquare(Square beginSquare, Square endSquare) {
        return beginSquare.equals(endSquare);
    }

    private List<Square> findPossibleSquares(Square beginSquare, boolean isDestinationNull) {
        List<Square> squares = new ArrayList<>();
        List<SquareNavigator> squareNavigators = getSquareNavigators(beginSquare, isDestinationNull);
        for (SquareNavigator navigator : squareNavigators) {
            squares.addAll(navigator.findSquares(board));
        }
        return squares;
    }

    private List<SquareNavigator> getSquareNavigators(Square beginSquare, boolean isDestinationNull) {
        Piece source = board.getPiece(beginSquare);
        if (source.isPieceOf(UnitType.PAWN))
            return ((Pawn) source).findSquareNavigators(beginSquare, isDestinationNull);
        return source.findSquareNavigators(beginSquare);
    }
}