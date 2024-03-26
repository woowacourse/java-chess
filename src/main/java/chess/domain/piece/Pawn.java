package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pawn extends Piece {
    public Pawn(final Color color, final Square square) {
        super(color, PieceType.PAWN, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> existPieces) {
        if (getColor() == Color.BLACK) {
            return findBlackPawnLegalMoves(existPieces);
        }
        return findWhitePawnLegalMoves(existPieces);
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

    private void addToMovableSquare(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        for (int i = 0; i < 2 && currentSquare.canMoveDown(); i++) {
            currentSquare = currentSquare.moveDown();
            if (isOccupied(existPieces, currentSquare)) {
                break;
            }
            squares.add(currentSquare);
        }
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

    private Piece getPiece(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == currentSquare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    private void addMovableSquareIfOccupiedEnemy(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        if (isOccupied(existPieces, currentSquare)) {
            Piece piece = getPiece(existPieces, currentSquare);
            addToMovableSquareIfEnemy(piece, squares, currentSquare);
        }
    }

    private void addToMovableSquareIfEnemy(Piece piece, Set<Square> squares, Square currentSquare) {
        if (isEnemyOf(piece)) {
            squares.add(currentSquare);
        }
    }

    private void addToMovableSquareIfBlank(Set<Piece> existPieces, Square currentSquare, Set<Square> squares) {
        if (isBlank(existPieces, currentSquare)) {
            squares.add(currentSquare);
        }
    }

    private boolean isOccupied(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .anyMatch(piece -> piece.currentSquare() == currentSquare);
    }

    private boolean isBlank(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .noneMatch(piece -> piece.currentSquare() == currentSquare);
    }

}
