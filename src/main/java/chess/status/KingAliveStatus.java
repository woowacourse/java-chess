package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;
import chess.piece.Piece;

public class KingAliveStatus implements GameStatus {

    private final Side turn;

    public KingAliveStatus(final Side turn) {
        this.turn = turn;
    }

    @Override
    public void validateMove(final ChessBoard chessBoard, final Position from, final Position to) {
        final Piece movingPiece = chessBoard.getPiece(from);
        validatePlayerTurn(movingPiece);
        chessBoard.validateMove(from, to);
    }

    private void validatePlayerTurn(final Piece movingPiece) {
        if (!turn.isTurnOf(movingPiece)) {
            throw new IllegalArgumentException("해당 기물의 턴이 아닙니다");
        }
    }

    @Override
    public GameStatus nextStatus(final ChessBoard chessBoard) {
        if (chessBoard.isKingDead()) {
            return new KingDeadStatus(turn);
        }
        return new KingAliveStatus(turn.nextTurn());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Side getWinner() {
        throw new UnsupportedOperationException("게임이 아직 진행 중입니다");
    }
}
