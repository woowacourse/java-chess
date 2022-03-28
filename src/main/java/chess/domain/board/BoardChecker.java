package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceNotation;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public final class BoardChecker {

    private static final int NEXT = 1;

    private final Map<Position, Piece> value;

    BoardChecker(final Map<Position, Piece> value) {
        this.value = value;
    }

    private boolean hasPieceInFile(final Position from, final Position to) {
        var minRank = Math.min(from.getRankNumber(), to.getRankNumber());
        var maxRank = Math.max(from.getRankNumber(), to.getRankNumber());

        return value.keySet().stream()
                .filter(position -> position.getFile() == from.getFile())
                .filter(position -> position.getRankNumber() > minRank)
                .anyMatch(position -> position.getRankNumber() < maxRank);
    }

    private boolean hasPieceInRank(final Position from, final Position to) {
        var minFile = Math.min(from.getFileOrder(), to.getFileOrder());
        var maxFile = Math.max(from.getFileOrder(), to.getFileOrder());

        return value.keySet().stream()
                .filter(position -> position.getRankNumber() == from.getRankNumber())
                .filter(position -> position.getFileOrder() > minFile)
                .anyMatch(position -> position.getFileOrder() < maxFile);
    }

    void checkPieceInDiagonal(final Position from, final Position to) {
        checkRisingDiagonal(from, to);
        checkDescendingDiagonal(from, to);
    }

    private void checkRisingDiagonal(final Position from, final Position to) {
        final var minFile = Math.min(from.getFileOrder(), to.getFileOrder());
        final var maxFile = Math.max(from.getFileOrder(), to.getFileOrder());
        int rank = Math.min(from.getRankNumber(), to.getRankNumber()) + NEXT;

        for (int file = minFile + NEXT; file < maxFile; file++, rank++) {
            checkHasPiece(rank, file);
        }
    }

    private void checkHasPiece(final int rank, final int file) {
        if (value.get(Position.of(File.from(file), Rank.from(rank))) != null) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    private void checkDescendingDiagonal(final Position from, final Position to) {
        final var minRank = Math.min(from.getRankNumber(), to.getRankNumber());
        final var maxRank = Math.max(from.getRankNumber(), to.getRankNumber());
        int file = Math.max(from.getFileOrder(), to.getFileOrder()) - NEXT;

        for (int rank = minRank + NEXT; rank < maxRank; rank++, file--) {
            checkHasPiece(rank, file);
        }
    }

    void checkPosition(final Position from, final Position to, final Color currentColor) {
        final var fromPiece = value.get(from);
        final var toPiece = value.get(to);

        checkFromPiece(fromPiece, currentColor);
        checkToPiece(toPiece, currentColor);
    }

    private void checkToPiece(final Piece piece, final Color currentColor) {
        if (piece != null && piece.isSameColor(currentColor)) {
            throw new IllegalArgumentException("도착지에 같은 색상의 기물이 존재합니다.");
        }
    }

    private void checkFromPiece(final Piece piece, final Color currentColor) {
        if (piece == null) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        if (!piece.isSameColor(currentColor)) {
            throw new IllegalArgumentException(currentColor + "차례 입니다.");
        }
    }

    void checkRemovedKing(final Piece piece) {
        if (piece != null && piece.getNotation() == PieceNotation.KING) {
            throw new IllegalStateException("상대편 king 이 제거 됐습니다.");
        }
    }

    public void checkHasPieceInLiner(final Position from, final Position to) {
        if (hasPieceInFile(from, to) || hasPieceInRank(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }
}
