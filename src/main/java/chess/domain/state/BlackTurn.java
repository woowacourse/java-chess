package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public final class BlackTurn extends Playing {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public GameState move(Position start, Position target) {
        Piece selected = board.getPiece(start);

        if (!selected.isBlack()) {
            throw new IllegalArgumentException(IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE);
        }

        return judgeStatus(board.movePiece(start, target));
    }

    private GameState judgeStatus(Piece targetPiece) {
        if (targetPiece.isKing()) {
            return new BlackWin(board);
        }
        return new WhiteTurn(board);
    }

    @Override
    public String findTurn() {
        return "흑";
    }

    @Override
    public String getState() {
        return "black_turn";
    }

    @Override
    public String getStateName() {
        return "흑팀 차례";
    }
}
