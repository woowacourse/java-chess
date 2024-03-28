package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT, Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
                Movement.LEFT_LEFT_UP, Movement.LEFT_LEFT_DOWN, Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP);
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
        if (currentSquare.canMoveUp() && currentSquare.moveUp().canMoveLeftUp()) {
            squares.add(currentSquare.moveUp().moveLeftUp());
        }
        if (currentSquare.canMoveUp() && currentSquare.moveUp().canMoveRightUp()) {
            squares.add(currentSquare.moveUp().moveRightUp());
        }
        if (currentSquare.canMoveDown() && currentSquare.moveDown().canMoveLeftDown()) {
            squares.add(currentSquare.moveDown().moveLeftDown());
        }
        if (currentSquare.canMoveDown() && currentSquare.moveDown().canMoveRightDown()) {
            squares.add(currentSquare.moveDown().moveRightDown());
        }
        if (currentSquare.canMoveRight() && currentSquare.moveRight().canMoveRightUp()) {
            squares.add(currentSquare.moveRight().moveRightUp());
        }
        if (currentSquare.canMoveRight() && currentSquare.moveRight().canMoveRightDown()) {
            squares.add(currentSquare.moveRight().moveRightDown());
        }
        if (currentSquare.canMoveLeft() && currentSquare.moveLeft().canMoveLeftUp()) {
            squares.add(currentSquare.moveLeft().moveLeftUp());
        }
        if (currentSquare.canMoveLeft() && currentSquare.moveLeft().canMoveLeftDown()) {
            squares.add(currentSquare.moveLeft().moveLeftDown());
        }
        return squares;
    }
}
