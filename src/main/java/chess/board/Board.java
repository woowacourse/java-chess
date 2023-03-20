package chess.board;

import chess.piece.Piece;
import chess.piece.Pieces;
import java.util.List;

public class Board {

    private final Pieces pieces;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        checkPieceMovable(targetPosition, sourcePiece);
        checkPath(targetPosition, sourcePiece);
        checkSameSidePieceOnTargetPosition(sourcePiece, targetPosition);
        checkOppositeSidePieceOnTargetPosition(sourcePiece, targetPosition);
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.synchronizeMovedPiece(sourcePiece, movedPiece);
    }

    private void checkPieceMovable(final Position targetPosition, final Piece sourcePiece) {
        if (!sourcePiece.isMovable(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
        }
    }

    private void checkPath(final Position targetPosition, final Piece sourcePiece) {
        final List<Position> paths = sourcePiece.getPaths(targetPosition);
        for (Position pathPosition : paths) {
            checkPieceExistOnPath(pathPosition);
        }
    }

    private void checkPieceExistOnPath(final Position pathPosition) {
        if (pieces.isPieceExistOnPosition(pathPosition)) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치까지의 경로에 말이 존재합니다.");
        }
    }

    private void checkSameSidePieceOnTargetPosition(final Piece sourcePiece, final Position targetPosition) {
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

    private void checkOppositeSidePieceOnTargetPosition(Piece sourcePiece, Position targetPosition) {
        if (pieces.isPieceExistOnPosition(targetPosition)) {
            final Piece targertPiece = pieces.findPieceByPosition(targetPosition);
            checkOppositeSidePiece(sourcePiece, targertPiece);
        }
    }

    private void checkOppositeSidePiece(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece.isOppositeSide(sourcePiece)) {
            pieces.remove(targetPiece);
        }
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }
}
