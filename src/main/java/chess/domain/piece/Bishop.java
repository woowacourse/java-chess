package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bishop extends Piece {

    public Bishop(final Color color, final Square square) {
        super(color, PieceType.BISHOP, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return Stream.of(candidateLeftUpSquares(entirePieces),
                        candidateLeftDownSquares(entirePieces),
                        candidateRightDownSquares(entirePieces),
                        candidateRightUpSquares(entirePieces))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<Square> candidateLeftUpSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeftUp()) {
            currentSquare = currentSquare.moveLeftUp();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
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

    private Set<Square> candidateLeftDownSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeftDown()) {
            currentSquare = currentSquare.moveLeftDown();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
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

    private Set<Square> candidateRightUpSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRightUp()) {
            currentSquare = currentSquare.moveRightUp();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
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

    private Set<Square> candidateRightDownSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRightDown()) {
            currentSquare = currentSquare.moveRightDown();
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
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
