package domain;

import domain.chess.piece.Piece;
import domain.chess.piece.Pieces;
import domain.chess.Color;
import domain.chess.Point;
import factory.ChessBoardGenerator;

import java.util.List;
import java.util.Optional;

public class ChessBoard {
    private final Pieces pieces;
    private Color turn;

    public ChessBoard(final Pieces pieces) {
        this(pieces, Color.WHITE);
    }

    public ChessBoard(final Pieces pieces, final Color color) {
        this.pieces = pieces;
        this.turn = color;
    }


    public void move(final Point startPoint, final Point endPoint) {
        validatePoint(startPoint, endPoint);
        this.pieces.findPieceWithPoint(startPoint)
                   .ifPresent(piece -> pieces.move(piece, endPoint));
        turn = turn.reverse();
    }

    private void validatePoint(final Point startPoint, final Point endPoint) {
        if (startPoint.equals(endPoint)) {
            throw new IllegalArgumentException("같은 위치로 이동할 수 없습니다.");
        }
        validatePiece(startPoint);
    }

    private void validatePiece(final Point startPoint) {
        final Optional<Piece> optionalPiece = this.pieces.findPieceWithPoint(startPoint);
        if (optionalPiece.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다", startPoint));
        }
        optionalPiece.ifPresent(this::validateDifferentColor);
    }

    private void validateDifferentColor(final Piece piece) {
        if (piece.isBlack() && turn.isBlack()) {
            return;
        }
        if (piece.isWhite() && turn.isWhite()) {
            return;
        }
        throw new IllegalArgumentException(String.format("현재는 %s의 차례입니다.", turn));
    }

    public static ChessBoard createDefaultBoard() {
        return ChessBoardGenerator.createDefaultBoard();
    }

    public List<Piece> getPieces() {
        return this.pieces.allPieces();
    }
}
