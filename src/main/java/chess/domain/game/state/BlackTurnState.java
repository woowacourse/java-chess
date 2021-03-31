package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public final class BlackTurnState extends TurnState {

    public BlackTurnState(final Board board) {
        super(board, Color.BLACK);
    }

    @Override
    protected GameState otherTurnState(final Board board) {
        return new WhiteTurnState(board);
    }
}
