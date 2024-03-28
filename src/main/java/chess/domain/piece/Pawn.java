package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public abstract class Pawn extends Piece {
    public Pawn(final Color color, final Square square) {
        super(color, PieceType.PAWN, square);
    }

    public abstract Set<Movement> capableOfAttackMovements();

    protected Set<Square> capableOfAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        for (Movement movement : capableOfAttackMovements()) {
            Square currentSquare = currentSquare();
            if (currentSquare.canMove(movement)) {
                currentSquare = currentSquare.move(movement);
                addMovableSquareIfOccupiedEnemy(existPieces, currentSquare, squares);
            }
        }
        return squares;
    }

    protected void addStartPawnMovableSquare(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        for (Movement movement : movements()) {
            for (int i = 0; i < 2 && currentSquare.canMove(movement); i++) {
                currentSquare = currentSquare.move(movement);
                if (isOccupied(existPieces, currentSquare)) {
                    break;
                }
                squares.add(currentSquare);
            }
        }
    }

    protected void addMovableSquare(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        for (Movement movement : movements()) {
            if (currentSquare.canMove(movement)) {
                currentSquare = currentSquare.move(movement);
                addToMovableSquareIfBlank(existPieces, currentSquare, squares);
            }
        }
    }

    protected Piece getPiece(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == currentSquare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    protected void addMovableSquareIfOccupiedEnemy(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        if (isOccupied(existPieces, currentSquare)) {
            Piece piece = getPiece(existPieces, currentSquare);
            addToMovableSquareIfEnemy(piece, squares, currentSquare);
        }
    }

    protected void addToMovableSquareIfEnemy(Piece piece, Set<Square> squares, Square currentSquare) {
        if (isEnemyOf(piece)) {
            squares.add(currentSquare);
        }
    }

    protected void addToMovableSquareIfBlank(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        if (isBlank(existPieces, currentSquare)) {
            squares.add(currentSquare);
        }
    }

    protected boolean isOccupied(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .anyMatch(piece -> piece.currentSquare() == currentSquare);
    }

    protected boolean isBlank(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .noneMatch(piece -> piece.currentSquare() == currentSquare);
    }
}
