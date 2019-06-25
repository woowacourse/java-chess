package chess.model;

import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

public class MoveHandler {
    private Board board;

    public MoveHandler(Board board) {
        this.board = board;
    }

    public boolean move(Square beginSquare, Square endSquare) {
        List<Square> squares = new ArrayList<>();
        Piece source = board.getPiece(beginSquare);
        List<SquareNavigator> squareNavigators = source.findSquareNavigators(beginSquare);
        for (SquareNavigator navigator : squareNavigators) {
            squares.addAll(navigator.findSquares(board));
        }
        return squares.contains(endSquare);
    }
}