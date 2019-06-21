package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static chess.model.rule.Rule.getPiece;

abstract class PieceRule {
    double pieceScore = 0;

    double getPieceScore(final Board board, final Position position) {
        return pieceScore;
    }

    abstract List<Square> getMovableSquares(final Board board, final Square square, final Piece piece);

    private List<Square> getOccupiedSquares(final Board board, final List<Square> candidatedSquares, final Piece piece) {
        return candidatedSquares.stream()
                .filter(square -> {
                    final Piece occupiedPiece = getPiece(board, square);
                    if (occupiedPiece != null) {
                        return occupiedPiece.getSide() == piece.getSide();
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    List<Square> getNonBlockedNeighbors(final Board board, final Square square, final Function<Square, Square> getNeighbors) {
        final List<Square> squareList = new ArrayList<>();
        Square nextSquare = getNeighbors.apply(square);
        while (nextSquare != null && getPiece(board, nextSquare) == null) {
            squareList.add(nextSquare);
            nextSquare = getNeighbors.apply(nextSquare);
        }
        return squareList;
    }

    boolean isValidMove(final Board board, final Piece piece, final Square checkTarget, final Square destination) {
        final List<Square> candidateList = getMovableSquares(board, checkTarget, piece);
        final List<Square> occupiedList = getOccupiedSquares(board, candidateList, piece);
        return candidateList.contains(destination) && !occupiedList.contains(destination);
    }
}
