package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiShift extends Piece {
    protected MultiShift(Color color, PieceType pieceType, Square square) {
        super(color, pieceType, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        for (Movement movement : movements()) {
            Square currentSquare = currentSquare();
            while (currentSquare.canMove(movement)) {
                currentSquare = currentSquare.move(movement);
                if (isOccupied(entirePieces, currentSquare)) {
                    Piece piece = getPiece(entirePieces, currentSquare);
                    addToMovableSquareIfEnemy(movableSquares, piece);
                    break;
                }
                movableSquares.add(currentSquare);
            }
        }
        return movableSquares;
    }

    private Piece getPiece(Set<Piece> entirePieces, Square existPieces) {
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == existPieces)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    private boolean isOccupied(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .anyMatch(piece -> piece.currentSquare() == currentSquare);
    }

    private void addToMovableSquareIfEnemy(Set<Square> movableSquare, Piece piece) {
        if (isEnemyOf(piece)) {
            movableSquare.add(piece.currentSquare());
        }
    }
}
