package chess.domain.chess;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;

import java.util.List;
import java.util.Map;

public final class ChessGame {
    private static final int ALL_KING_ALIVE_COUNT = 2;

    private final ChessBoard chessBoard;
    private CampType currentCamp;

    public ChessGame() {
        this.chessBoard = ChessBoard.getInstance(this);
    }

    public boolean run(final Position source, final Position target, final CampType currentCamp) {
        this.currentCamp = currentCamp;
        return play(source, target);
    }

    public Map<Position, Piece> getWhiteBoard() {
        return chessBoard.getBoardByCamp(CampType.WHITE);
    }

    public Map<Position, Piece> getBlackBoard() {
        return chessBoard.getBoardByCamp(CampType.BLACK);
    }

    private boolean play(final Position source, final Position target) {
        validateCamp(source);
        validateTargetSameCamp(target);
        validateObstacle(source, target);
        if (!canAttack(source, target) && !canMove(source, target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        movePiece(source, target);
        return isKingAlive();
    }

    private void validateObstacle(final Position source, final Position target) {
        boolean isPossibleRoute = chessBoard.isPossibleRoute(source, target);
        if (!isPossibleRoute) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateCamp(final Position source) {
        validatePieceExistence(source);
        final Piece piece = chessBoard.getPiece(source);
        if (!piece.isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다. 현재 차례 = " + currentCamp.name());
        }
    }

    private void validateTargetSameCamp(final Position target) {
        if (!chessBoard.contains(target)) {
            return;
        }
        final Piece piece = chessBoard.getPiece(target);
        if (piece.isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private boolean canAttack(final Position source, final Position target) {
        final Piece sourcePiece = chessBoard.getPiece(source);
        final boolean isTargetExist = chessBoard.contains(target);
        return sourcePiece.canAttack(source, target, isTargetExist);
    }

    private boolean canMove(final Position source, final Position target) {
        final Piece piece = chessBoard.getPiece(source);
        boolean isTargetExist = chessBoard.contains(target);
        return piece.canMove(source, target, isTargetExist);
    }

    private void movePiece(final Position source, final Position target) {
        final Piece piece = chessBoard.getPiece(source);
        chessBoard.removePiece(source);
        chessBoard.putPiece(target, piece);
    }

    private void validatePieceExistence(final Position position) {
        if (!chessBoard.contains(position)) {
            throw new IllegalArgumentException("체스말이 존재하는 위치를 입력해 주세요.");
        }
    }

    private boolean isKingAlive() {
        List<Piece> aliveKings = chessBoard.getAliveKings();
        return aliveKings.size() == ALL_KING_ALIVE_COUNT;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
