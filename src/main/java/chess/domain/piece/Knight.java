package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(final Color color, final Square square) {
        super(color, PieceType.KNIGHT, square);
    }

    @Override
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
        squares.add(currentSquare.moveUp().moveLeftUp());
        squares.add(currentSquare.moveUp().moveRightUp());
        squares.add(currentSquare.moveDown().moveLeftDown());
        squares.add(currentSquare.moveDown().moveRightDown());
        squares.add(currentSquare.moveRight().moveRightUp());
        squares.add(currentSquare.moveRight().moveRightDown());
        squares.add(currentSquare.moveLeft().moveLeftUp());
        squares.add(currentSquare.moveLeft().moveLeftDown());
        return squares;
    }
}
