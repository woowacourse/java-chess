package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiShift extends Piece {
    protected MultiShift(Color color, PieceType pieceType, Square square) {
        super(color, pieceType, square);
    }

    protected Set<Square> candidateUpSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveUp()) {
            currentSquare = currentSquare.moveUp();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateDownSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveDown()) {
            currentSquare = currentSquare.moveDown();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateRightSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRight()) {
            currentSquare = currentSquare.moveRight();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateLeftSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeft()) {
            currentSquare = currentSquare.moveLeft();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateLeftUpSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeftUp()) {
            currentSquare = currentSquare.moveLeftUp();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateLeftDownSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeftDown()) {
            currentSquare = currentSquare.moveLeftDown();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateRightUpSquares(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRightUp()) {
            currentSquare = currentSquare.moveRightUp();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
        return movableSquares;
    }

    protected Set<Square> candidateRightDownSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRightDown()) {
            currentSquare = currentSquare.moveRightDown();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(squares, piece);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
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
