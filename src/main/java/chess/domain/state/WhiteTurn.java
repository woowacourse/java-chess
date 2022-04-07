package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public final class WhiteTurn extends Playing {

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public GameState move(Position start, Position target) {
        Piece selected = board.getPiece(start);

        if (selected.isBlack()) {
            throw new IllegalArgumentException(IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE);
        }

        return judgeStatus(board.movePiece(start, target));
    }

    private GameState judgeStatus(Piece targetPiece) {
        if (targetPiece.isKing()) {
            return new WhiteWin(board);
        }
        return new BlackTurn(board);
    }

    @Override
    public String findTurn() {
        return "백";
    }

    @Override
    public String getState() {
        return "white_turn";
    }

    @Override
    public String getStateName() {
        return "백팀 차례";
    }
}
