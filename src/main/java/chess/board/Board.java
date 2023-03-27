package chess.board;

import java.util.List;

import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Pieces;

public class Board {

    private final Pieces pieces;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPosition(final Position position) {
        return pieces.findPieceByPosition(position);
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        checkPieceMovable(targetPosition, sourcePiece);
        checkPath(targetPosition, sourcePiece);
        checkTargetPosition(targetPosition, sourcePiece);
        if (sourcePiece.isNeedToCheckWhenDiagonalMove()) {
            checkPieceDiagonalMove(sourcePosition, targetPosition);
        }
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.synchronizeMovedPiece(sourcePiece, movedPiece);
    }

    private void checkPieceDiagonalMove(Position sourcePosition, Position targetPosition) {
        Direction direction = sourcePosition.getDirectionTo(targetPosition);
        if (direction.isDiagonalMovable() && !pieces.isPieceExistOnPosition(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 대각선에 적팀 기물이 있을 때만 이동할 수 있습니다.");
        }
    }

    private void checkPieceMovable(final Position targetPosition, final Piece sourcePiece) {
        if (!sourcePiece.isMovable(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
        }
    }

    private void checkPath(final Position targetPosition, final Piece sourcePiece) {
        final List<Position> paths = sourcePiece.getPaths(targetPosition);
        boolean isExistPieceOnPath = paths.stream().anyMatch(pieces::isPieceExistOnPosition);
        if (isExistPieceOnPath) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치까지의 경로에 말이 존재합니다.");
        }
    }

    private void checkTargetPosition(final Position targetPosition, final Piece sourcePiece) {
        if (pieces.isPieceExistOnPosition(targetPosition)) {
            final Piece targetPiece = pieces.findPieceByPosition(targetPosition);
            checkSameSidePiece(sourcePiece, targetPiece);
        }
    }

    private void checkSameSidePiece(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece.isSameSide(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치에 아군 말이 존재합니다.");
        }
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }
}
