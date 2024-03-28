package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pawn extends Piece {
    public Pawn(final Color color, final Square square) {
        super(color, PieceType.PAWN, square);
    }

    public abstract Set<Movement> capableOfAttackMovements();

    protected Set<Square> capableOfAttack(Set<Piece> existPieces) {
        return capableOfAttackMovements().stream()
                .filter(movement -> currentSquare().canMove(movement))
                .map(movement -> currentSquare().move(movement))
                .filter(square -> isOccupied(existPieces, square) && isEnemyOf(getPiece(existPieces, square)))
                .collect(Collectors.toSet());
    }

    protected void addStartPawnMovableSquare(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {//todo
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

    protected Set<Square> findPawnLegalMoves(Set<Piece> existPieces, Color color) {
        Set<Square> squares = new HashSet<>(capableOfAttack(existPieces));
        Square currentSquare = currentSquare();
        if (currentSquare.isStartRankOf(color)) {
            addStartPawnMovableSquare(existPieces, currentSquare, squares);
            return squares;
        }
        squares.addAll(addMovableSquare(existPieces, currentSquare));
        return squares;
    }

    protected Set<Square> addMovableSquare(Set<Piece> existPieces, Square currentSquare) {
        return movements().stream()
                .filter(currentSquare::canMove)
                .map(currentSquare::move)
                .filter(square -> isBlank(existPieces, square))
                .collect(Collectors.toSet());
    }

    protected Piece getPiece(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == currentSquare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
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
