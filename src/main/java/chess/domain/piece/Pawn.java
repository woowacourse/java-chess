package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends AbstractPawn {
    public Pawn(final Color color, final Square square) {
        super(color, square);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<Direction> directions = Direction.ofPawn(color);
        return directions.stream()
                .map(source::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Square> findLegalMoves(Set<Piece> existPieces) {
        if (getColor() == Color.BLACK) {
            return findBlackPawnLegalMoves(existPieces);
        }
        return findWhitePawnLegalMoves(existPieces);
    }

    private Set<Square> findBlackPawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Set<Square> capableOfAttack = findBlackCapableOfAttack(existPieces);
        squares.addAll(capableOfAttack);
        Square currentSquare = currentSquare();

        if (currentSquare.isStartRankOfBlackPawn()) {
            for (int i = 0; i < 2 && currentSquare.canMoveDown(); i++) {
                currentSquare = currentSquare.moveDown();
                if (isOccupied(existPieces, currentSquare)) {
                    break;
                }
                squares.add(currentSquare);
            }
            return squares;
        }
        if (currentSquare.canMoveDown()) {
            currentSquare = currentSquare.moveDown();
            if (!isOccupied(existPieces, currentSquare)) {
                squares.add(currentSquare);
            }
        }
        return squares;
    }

    private Set<Square> findBlackCapableOfAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveLeftDown()) {
            currentSquare = currentSquare.moveLeftDown();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isEnemyOf(piece)) {
                    squares.add(currentSquare);
                }
            }
        }

        currentSquare = currentSquare();
        if (currentSquare.canMoveRightDown()) {
            currentSquare = currentSquare.moveRightDown();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isEnemyOf(piece)) {
                    squares.add(currentSquare);
                }
            }
        }
        return squares;
    }

    private Set<Square> findWhiteCapableOfAttack(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        if (currentSquare.canMoveLeftUp()) {
            currentSquare = currentSquare.moveLeftUp();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isEnemyOf(piece)) {
                    squares.add(currentSquare);
                }
            }
        }

        currentSquare = currentSquare();
        if (currentSquare.canMoveRightUp()) {
            currentSquare = currentSquare.moveRightUp();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isEnemyOf(piece)) {
                    squares.add(currentSquare);
                }
            }
        }
        return squares;
    }

    private Set<Square> findWhitePawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Set<Square> capableOfAttack = findWhiteCapableOfAttack(existPieces);
        squares.addAll(capableOfAttack);
        Square currentSquare = currentSquare();
        if (currentSquare.isStartRankOfWhitePawn()) {
            for (int i = 0; i < 2 && currentSquare.canMoveUp(); i++) {
                currentSquare = currentSquare.moveUp();
                if (isOccupied(existPieces, currentSquare)) {
                    break;
                }
                squares.add(currentSquare);
            }
            return squares;
        }
        if (currentSquare.canMoveUp()) {
            currentSquare = currentSquare.moveUp();
            if (!isOccupied(existPieces, currentSquare)) {
                squares.add(currentSquare);
            }
        }
        return squares;
    }

    private Piece getPiece(Set<Piece> entirePieces, Square currentSquare) { //todo 예외를 던지는 것이 아닌, Blank를 반환하기
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == currentSquare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    private boolean isOccupied(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .anyMatch(piece -> piece.currentSquare() == currentSquare);
    }

}
