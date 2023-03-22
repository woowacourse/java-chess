package chess.domain.board;

import java.util.List;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

public class Board {

    private final Pieces pieces;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPosition(final Position position) {
        return pieces.findPieceByPosition(position);
    }

    public void checkPieceMoveCondition(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        checkPieceMovable(targetPosition, sourcePiece);
        checkPath(targetPosition, sourcePiece);
        checkTargetPositionPieceSide(targetPosition, sourcePiece);
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

    private void checkTargetPositionPieceSide(final Position targetPosition, final Piece sourcePiece) {
        if (isExistPieceOnPosition(targetPosition)) {
            final Piece targetPiece = pieces.findPieceByPosition(targetPosition);
            checkSameSidePiece(sourcePiece, targetPiece);
        }
    }

    private boolean isExistPieceOnPosition(Position targetPosition) {
        return pieces.isPieceExistOnPosition(targetPosition);
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        if (sourcePiece.isNeedToCheckWhenDiagonalMove()) {
            checkPieceDiagonalMove(sourcePosition, targetPosition);
            checkPieceLinearMove(sourcePosition, targetPosition);
        }
        takePieceOrOnlyMove(targetPosition, sourcePiece);
    }

    private void checkPieceDiagonalMove(Position sourcePosition, Position targetPosition) {
        Direction direction = sourcePosition.getDirectionTo(targetPosition);
        if (direction.isDiagonalMovable() && !isExistPieceOnPosition(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 대각선에 적팀 기물이 있을 때만 이동할 수 있습니다.");
        }
    }

    private void checkPieceLinearMove(Position sourcePosition, Position targetPosition) {
        Direction direction = sourcePosition.getDirectionTo(targetPosition);
        if (direction.isVerticalMovable() && isExistPieceOnPosition(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 직선 상의 도착 위치 기물이 적군이어도 이동할 수 없습니다.");
        }
    }

    private void takePieceOrOnlyMove(Position targetPosition, Piece sourcePiece) {
        if (isExistPieceOnPosition(targetPosition)) {
            takePieceMove(targetPosition, sourcePiece);
        }
        if (!isExistPieceOnPosition(targetPosition)) {
            move(targetPosition, sourcePiece);
        }
    }

    private void takePieceMove(Position targetPosition, Piece sourcePiece) {
        Piece oppositeTargetPiece = pieces.findPieceByPosition(targetPosition);
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.removePiece(oppositeTargetPiece);
        pieces.changePiece(sourcePiece, movedPiece);
    }

    private void move(Position targetPosition, Piece sourcePiece) {
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.changePiece(sourcePiece, movedPiece);
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
