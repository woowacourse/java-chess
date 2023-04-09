package chess.domain.status;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceType;

public class KingAliveStatus implements GameStatus {

    private final Color turn;

    public KingAliveStatus(final Color turn) {
        this.turn = turn;
    }

    @Override
    public void validatePlayerTurn(ChessBoard chessBoard, Position from) {
        if (chessBoard.isPieceColorNotMatch(from, turn)) {
            throw new IllegalArgumentException("해당 기물의 턴이 아닙니다");
        }
    }

    @Override
    public GameStatus nextStatus(final Piece capturedPiece) {
        if (PieceType.isKing(capturedPiece.getPieceType())) {
            return new KingDeadStatus(turn);
        }
        return new KingAliveStatus(turn.nextTurn());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Color getWinner() {
        throw new UnsupportedOperationException("게임이 아직 진행 중입니다");
    }

    @Override
    public Color getTurn() {
        return turn;
    }
}
