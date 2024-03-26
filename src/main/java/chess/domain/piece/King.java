package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(final Color color, Square square) {
        super(color, PieceType.KING, square);
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
        if (candidateSquares.contains(other.currentSquare()) && isAllyOf(other)) {
            candidateSquares.remove(other.currentSquare());
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
