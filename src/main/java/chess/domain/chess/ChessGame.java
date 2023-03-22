package chess.domain.chess;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;

import java.util.Map;

public final class ChessGame {
    private final ChessBoard chessBoard;
    private CampType currentCamp;

    public ChessGame() {
        this.chessBoard = ChessBoard.getInstance(this);
    }

    public void setUp(final Position source, final Position target, final CampType currentCamp) {
        this.currentCamp = currentCamp;
        play(source, target);
    }

    private void play(final Position source, final Position target) {
        validateCamp(source);
        validateTargetSameCamp(target);
        validateObstacle(source, target);
        if (!canAttack(source, target) && !canMove(source, target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        movePiece(source, target);
    }

    private void validateObstacle(final Position source, final Position target) {
        boolean isPossibleRoute = chessBoard.isPossibleRoute(source, target);
        if (!isPossibleRoute) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateCamp(final Position source) {
        final Piece sourcePiece = chessBoard.checkPiece(source);
        if (!sourcePiece.isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다. 현재 차례 = " + currentCamp.name());
        }
    }

    private void validateTargetSameCamp(final Position target) {
        final Piece targetPiece = chessBoard.checkPiece(target);
        if (chessBoard.contains(target) && targetPiece.isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private boolean canAttack(final Position source, final Position target) {
        final Piece sourcePiece = chessBoard.checkPiece(source);
        final boolean isTargetExist = chessBoard.contains(target);
        return sourcePiece.canAttack(source, target, isTargetExist);
    }

    private boolean canMove(final Position source, final Position target) {
        final Piece piece = chessBoard.checkPiece(source);
        boolean isTargetExist = chessBoard.contains(target);
        return piece.canMove(source, target, isTargetExist);
    }

    private void movePiece(final Position source, final Position target) {
        final Piece piece = chessBoard.checkPiece(source);
        chessBoard.removePiece(source);
        chessBoard.putPiece(target, piece);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
