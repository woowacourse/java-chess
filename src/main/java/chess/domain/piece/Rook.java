package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rook extends Piece {
    public Rook(final Color color, Square square) {
        super(color, PieceType.ROOK, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return Stream.of(candidateUpSquares(entirePieces),
                        candidateDownSquares(entirePieces),
                        candidateLeftSquares(entirePieces),
                        candidateRightSquares(entirePieces))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private Set<Square> candidateUpSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveUp()) {
            currentSquare = currentSquare.moveUp();
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

    private Set<Square> candidateDownSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveDown()) {
            currentSquare = currentSquare.moveDown();
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

    private Set<Square> candidateRightSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveRight()) {
            currentSquare = currentSquare.moveRight();
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

    private Set<Square> candidateLeftSquares(Set<Piece> entirePieces) {
        Set<Square> squares = new HashSet<>();
        Square currentSquare = currentSquare();
        while (currentSquare.canMoveLeft()) {
            currentSquare = currentSquare.moveLeft();
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
