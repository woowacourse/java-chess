package chess.domain.chess;

import chess.domain.board.ChessBoard;
import chess.domain.camp.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public final class ChessGame {
    private final ChessBoard chessBoard;
    private TeamColor currentTeamColor;

    public ChessGame() {
        this.chessBoard = ChessBoard.getInstance(this);
    }

    public void setUp(final Position source, final Position target, final TeamColor teamColor) {
        this.currentTeamColor = teamColor;
        play(source, target);
    }

    public void play(final Position source, final Position target) {
        final Piece piece = chessBoard.getPiece(source);
        if (piece == null) {
            throw new IllegalArgumentException("기물이 존재하는 위치를 입력해주세요.");
        }
        validateCamp(piece);
        if (!piece.canMove(source, target, chessBoard.getPiece(target))
                || !chessBoard.isPossibleRoute(source, target, currentTeamColor)) {
            throw new IllegalArgumentException("기물 규칙 상 움직일 수 없는 위치입니다.");
        }
        movePiece(source, target, piece);
    }

    private void validateCamp(final Piece piece) {
        if (!piece.isSameTeam(currentTeamColor)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
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
