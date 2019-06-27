package chess.model.rule;

import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                    final Optional<Piece> occupiedPiece = getPiece(board, square);
                    if (occupiedPiece.isPresent()) {
                        return occupiedPiece.get().getSide() == piece.getSide();
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    List<Square> getNonBlockedNeighbors(final Board board, final Square square, final Function<Square, Optional<Square>> getNeighbor) {
        final List<Square> squareList = new ArrayList<>();
        final Side side = getPiece(board, square).get().getSide();
        Optional<Square> nextSquare = getNeighbor.apply(square);
        while (nextSquare.isPresent() && getPiece(board, nextSquare.get()).isEmpty()) {
            squareList.add(nextSquare.get());
            nextSquare = getNeighbor.apply(nextSquare.get());
        }
        if (checkNextSquareInEnemy(board, nextSquare, side)) {
            squareList.add(nextSquare.get());
        }
        return squareList;
    }

    private boolean checkNextSquareInEnemy(
            final Board board, final Optional<Square> nextSquare, final Side side
    ) {
        if (nextSquare.isPresent()) {
            final Optional<Piece> piece = getPiece(board, nextSquare.get());
            return piece.isPresent() && piece.get().getSide() != side;
        }
        return false;
    }

    boolean isValidMove(final Board board, final Piece piece, final Square checkTarget, final Square destination) {
        final List<Square> candidateList = getMovableSquares(board, checkTarget, piece);
        final List<Square> occupiedList = getOccupiedSquares(board, candidateList, piece);
        return candidateList.contains(destination) && !occupiedList.contains(destination);
    }
}
