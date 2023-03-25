package chess.board;

import chess.piece.Direction;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.Side;
import java.util.List;

public class Board {

    private static final int SIDE_LENGTH = 8;

    private final Pieces pieces;
    private Side turnToMove;

    public Board(final Pieces pieces, Side firstTurn) {
        this.pieces = pieces;
        this.turnToMove = firstTurn;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        checkTurnToMoveBySide(sourcePiece.getSide());
        checkSameSidePieceOnTargetPosition(sourcePiece, targetPosition);
        checkPath(targetPosition, sourcePiece);
        checkPieceMovable(sourcePosition, targetPosition);
        checkOppositeSidePieceOnTargetPosition(sourcePiece, targetPosition);
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.synchronizeMovedPiece(sourcePiece, movedPiece);
        changeTurnToMove();
    }

    private void checkTurnToMoveBySide(final Side sourcePieceSide) {
        if (sourcePieceSide != turnToMove) {
            throw new IllegalArgumentException("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
        }
    }

    private void checkPieceMovable(final Position sourcePosition, final Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        if (!sourcePiece.isMovable(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
        }
        if (sourcePiece.getClass() == Pawn.class) {
            final Direction direction = sourcePosition.getDirectionTo(targetPosition);
            final boolean isPieceOnTargetPosition = pieces.isPieceExistOnPosition(targetPosition);
            checkConditionOfPawnVerticalMove(direction, isPieceOnTargetPosition);
            checkConditionOfPawnDiagonalMove(direction, isPieceOnTargetPosition);
        }
    }

    private void checkConditionOfPawnVerticalMove(final Direction direction, final boolean isPieceOnTargetPosition) {
        if (direction.isVertical() && isPieceOnTargetPosition) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각으로 이동할 때만 적 기물이 있는 위치로 이동할 수 있습니다.");
        }
    }

    private void checkConditionOfPawnDiagonalMove(final Direction direction, final boolean isPieceOnTargetPosition) {
        if (direction.isDiagonal() && !isPieceOnTargetPosition) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각 방향에 적이 있을 때만 대각으로 이동할 수 있습니다.");
        }
    }

    private void checkPath(final Position targetPosition, final Piece sourcePiece) {
        if (sourcePiece.canPassThrough()) {
            return;
        }
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

    private void changeTurnToMove() {
        if (turnToMove == Side.BLACK) {
            turnToMove = Side.WHITE;
            return;
        }
        turnToMove = Side.BLACK;
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }

    public int getSideLength() {
        return SIDE_LENGTH;
    }
}
