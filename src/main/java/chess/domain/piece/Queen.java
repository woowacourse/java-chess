package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {
    public Queen(final Color color, final Square square) {
        super(color, PieceType.QUEEN, square);
    }

    public Set<Square> findLegalMoves(Set<Piece> existPieces) {
        return Stream.of(candidateUpSquares(existPieces),
                        candidateDownSquares(existPieces),
                        candidateLeftSquares(existPieces),
                        candidateRightSquares(existPieces),
                        candidateLeftUpSquares(existPieces),
                        candidateLeftDownSquares(existPieces),
                        candidateRightDownSquares(existPieces),
                        candidateRightUpSquares(existPieces))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<Square> candidateUpSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveUp()) {
            currentSquare = currentSquare.moveUp();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateDownSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveDown()) {
            currentSquare = currentSquare.moveDown();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateRightSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRight()) {
            currentSquare = currentSquare.moveRight();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateLeftSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeft()) {
            currentSquare = currentSquare.moveLeft();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateLeftUpSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeftUp()) {
            currentSquare = currentSquare.moveLeftUp();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateLeftDownSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeftDown()) {
            currentSquare = currentSquare.moveLeftDown();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateRightUpSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRightUp()) {
            currentSquare = currentSquare.moveRightUp();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Set<Square> candidateRightDownSquares(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRightDown()) {
            currentSquare = currentSquare.moveRightDown();
            if (isOccupied(existPieces, currentSquare)) {
                Piece piece = getPiece(existPieces, currentSquare);
                if (isAllyOf(piece)) {
                    break;
                }
                squares.add(currentSquare);
                break;
            }
            squares.add(currentSquare);
        }
        return squares;
    }

    private Piece getPiece(Set<Piece> entirePieces, Square existPieces) { //todo 예외를 던지는 것이 아닌, Blank를 반환하기
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == existPieces)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    private boolean isOccupied(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .anyMatch(piece -> piece.currentSquare() == currentSquare);
    }
}
