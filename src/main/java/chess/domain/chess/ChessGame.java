package chess.domain.chess;

import chess.domain.board.ChessBoard;
import chess.domain.camp.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Map;

public final class ChessGame {
    private final ChessBoard chessBoard;
    private TeamColor currentCamp;

    public ChessGame() {
        this.chessBoard = ChessBoard.getInstance(this);
    }

    public void setUp(final Position source, final Position target, final TeamColor currentCamp) {
        this.currentCamp = currentCamp;
        play(source, target);
    }

    public void play(final Position source, final Position target) {
        final Piece piece = chessBoard.getPiece(source);
        validateCamp(piece);
        if (piece.isPawn()) {
            movePawn(source, target, piece);
            return;
        }
        if (piece.isKnight() && piece.canMove(source, target)) {
            movePiece(source, target, piece);
            return;
        }
        validatePossibleRoute(source, target, piece);
        movePiece(source, target, piece);
    }

    private void validateCamp(final Piece piece) {
        if (!piece.isSameCamp(currentCamp)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    /**
     * 시작에서 목표 지점까지 이동이 가능한지, 그리고 그 사이에 장애물이 존재하지 않는지 체크한다.
     *
     * @param source 시작 지점
     * @param target 도착 지점
     * @param piece  체스 말
     */
    private void validatePossibleRoute(final Position source, final Position target, final Piece piece) {
        if (!piece.canMove(source, target) && !chessBoard.isPossibleRoute(source, target, piece)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
    }

    /**
     * 폰의 이동 규칙을 관리한다.
     * 1) 첫 시도 - 대각선 위치에 다른 편의 기물 있음 : 공격
     * 2) 첫 시도 - 대각선 위치에 기물 없음 : 최대 2칸까지 이동 가능, 가능한 위치면 이동
     * 3) 그 외 - 대각선 위치에 기물 있음 : 공격
     * 4) 그 외 - 대각선 위치에 기물 없음 : 앞으로 1칸 이동
     *
     * @param source 시작 위치
     * @param target 종료 위치
     * @param piece  체스 말
     *                                                         TODO : 여기서 책임을 가지는 건 무의미한 것 같은데, 어떻게 분리하지?
     */
    private void movePawn(final Position source, final Position target, final Piece piece) {
        if ((piece.isSameCamp(TeamColor.WHITE) && source.getRank() == 1) || (piece.isSameCamp(TeamColor.BLACK) && source.getRank() == 6)) {
            attackOrMove(source, target, piece);
            return;
        }
        if (canAttack(source, target)) {
            movePiece(source, target, piece);
            return;
        }
        validatePawnOneMove(source, target);
        movePiece(source, target, piece);
    }

    private void attackOrMove(final Position source, final Position target, final Piece piece) {
        if (canAttack(source, target)) {
            movePiece(source, target, piece);
            return;
        }
        validatePawnMoveCondition(source, target, piece);
        movePiece(source, target, piece);
    }

    private void validatePawnOneMove(final Position source, final Position target) {
        if (Math.abs(target.getRank() - source.getRank()) != 1 || chessBoard.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private boolean canAttack(final Position source, final Position target) {
        if (Math.abs(target.getRank() - source.getRank()) == 1 && Math.abs(target.getFile() - source.getFile()) == 1) {
            if (!chessBoard.contains(target) || chessBoard.getPiece(target).isSameCamp(currentCamp)) {
                throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
            }
        }
        return true;
    }

    private void validatePawnMoveCondition(final Position source, final Position target, final Piece piece) {
        if (!piece.canMove(source, target) || chessBoard.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        chessBoard.removePiece(source);
        chessBoard.putPiece(target, piece);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
