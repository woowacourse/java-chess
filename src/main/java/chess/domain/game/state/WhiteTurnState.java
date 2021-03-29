package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public final class WhiteTurnState extends TurnState {

    public WhiteTurnState(final Board board) {
        super(board, Color.WHITE);
    }

    @Override
    protected GameState otherTurnState(final Board board) {
        return new BlackTurnState(board);
    }
}
