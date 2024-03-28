package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlackPawn extends Pawn {

    public BlackPawn(Square square) {
        super(Color.BLACK, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return findBlackPawnLegalMoves(entirePieces);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.DOWN, Movement.DOWN_DOWN, Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    }

    private Set<Square> findBlackPawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>(findBlackCapableOfAttack(existPieces));
        Square currentSquare = currentSquare();
        if (currentSquare.isStartRankOfBlackPawn()) {
            addToMovableSquare(existPieces, currentSquare, squares);
            return squares;
        }
        if (currentSquare.canMoveDown()) {
            currentSquare = currentSquare.moveDown();
            addToMovableSquareIfBlank(existPieces, currentSquare, squares);
        }
        return squares;
    }

    private Set<Square> findBlackCapableOfAttack(Set<Piece> existPieces) {
        Set<Square> leftDownAttack = findLeftDownAttack(existPieces);
        Set<Square> rightDownAttack = rightDownAttack(existPieces);
        return Stream.concat(leftDownAttack.stream(), rightDownAttack.stream())
                .collect(Collectors.toSet());
    }

    private Set<Square> findLeftDownAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveLeftDown()) {
            currentSquare = currentSquare.moveLeftDown();
            addMovableSquareIfOccupiedEnemy(existPieces, currentSquare, squares);
        }
        return squares;
    }

    private Set<Square> rightDownAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveRightDown()) {
            currentSquare = currentSquare.moveRightDown();
            addMovableSquareIfOccupiedEnemy(existPieces, currentSquare, squares);
        }
        return squares;
    }
}
