package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Set.of(Movement.UP, Movement.UP_UP, Movement.RIGHT_UP, Movement.LEFT_UP);
    }

    private Set<Square> findWhitePawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>(findWhiteCapableOfAttack(existPieces));
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

    private Set<Square> findWhiteCapableOfAttack(Set<Piece> existPieces) {
        Set<Square> leftUpAttack = leftUpAttack(existPieces);
        Set<Square> rightUpAttack = rightUpAttack(existPieces);
        return Stream.concat(leftUpAttack.stream(), rightUpAttack.stream())
                .collect(Collectors.toSet());
    }

    private Set<Square> rightUpAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveRightUp()) {
            currentSquare = currentSquare.moveRightUp();
            addMovableSquareIfOccupiedEnemy(existPieces, currentSquare, squares);
        }
        return squares;
    }

    private Set<Square> leftUpAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveLeftUp()) {
            currentSquare = currentSquare.moveLeftUp();
            addMovableSquareIfOccupiedEnemy(existPieces, currentSquare, squares);
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
