package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
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

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
                Movement.LEFT_UP, Movement.LEFT_DOWN, Movement.RIGHT_UP, Movement.RIGHT_DOWN);
    }

    public void removeIfAllyExist(Piece other, Set<Square> candidateSquares) {
        if (candidateSquares.contains(other.currentSquare()) && isAllyOf(other)) {
            candidateSquares.remove(other.currentSquare());
        }
    }

    public Set<Square> candidateSquares() {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveUp()) {
            squares.add(currentSquare.moveUp());
        }
        if (currentSquare.canMoveDown()) {
            squares.add(currentSquare.moveDown());
        }
        if (currentSquare.canMoveLeft()) {
            squares.add(currentSquare.moveLeft());
        }
        if (currentSquare.canMoveRight()) {
            squares.add(currentSquare.moveRight());
        }
        if (currentSquare.canMoveLeftUp()) {
            squares.add(currentSquare.moveLeftUp());
        }
        if (currentSquare.canMoveLeftDown()) {
            squares.add(currentSquare.moveLeftDown());
        }
        if (currentSquare.canMoveRightUp()) {
            squares.add(currentSquare.moveRightUp());
        }
        if (currentSquare.canMoveRightDown()) {
            squares.add(currentSquare.moveRightDown());
        }
        return squares;
    }
}
