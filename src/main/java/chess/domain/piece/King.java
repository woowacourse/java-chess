package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends UnslidingPiece {

    public King(final Color color, Square square) {
        super(color, PieceType.KING, square);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<Direction> directions = Direction.all();
        return directions.stream()
                .map(source::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        Set<Square> candidateSquares = candidateSquares();
        for (Piece other : entirePieces) {
            removeIfAllyExist(other, candidateSquares);
        }
        return candidateSquares;
    }

    public void removeIfAllyExist(Piece other, Set<Square> candidateSquares) {
        if (candidateSquares.contains(other.currentSquare())) {
            if (isAllyOf(other)) {
                candidateSquares.remove(other.currentSquare());
            }
        }
    }

    public Set<Square> candidateSquares() {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        squares.add(currentSquare.moveUp());
        squares.add(currentSquare.moveDown());
        squares.add(currentSquare.moveLeft());
        squares.add(currentSquare.moveRight());
        squares.add(currentSquare.moveLeftUp());
        squares.add(currentSquare.moveLeftDown());
        squares.add(currentSquare.moveRightUp());
        squares.add(currentSquare.moveRightDown());
        return squares;
    }
}
