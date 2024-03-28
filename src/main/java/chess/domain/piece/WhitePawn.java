package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public class WhitePawn extends Pawn {

    public WhitePawn(Square square) {
        super(Color.WHITE, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return findWhitePawnLegalMoves(entirePieces);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP, Movement.UP_UP);
    }

    @Override
    public Set<Movement> capableOfAttackMovements() {
        return Set.of(Movement.LEFT_UP, Movement.RIGHT_UP);
    }

    private Set<Square> findWhitePawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>(capableOfAttack(existPieces));
        Square currentSquare = currentSquare();
        if (currentSquare.isStartRankOfWhitePawn()) {
            return findStartWhitePawnLegalMoves(existPieces, currentSquare, squares);
        }
        if (currentSquare.canMoveUp()) {
            currentSquare = currentSquare.moveUp();
            addToMovableSquareIfBlank(existPieces, currentSquare, squares);
        }
        return squares;
    }



    private Set<Square> findStartWhitePawnLegalMoves(Set<Piece> existPieces, Square currentSquare,
                                                     Set<Square> squares) {
        for (int i = 0; i < 2 && currentSquare.canMoveUp(); i++) {
            currentSquare = currentSquare.moveUp();
            if (isOccupied(existPieces, currentSquare)) {
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

}
