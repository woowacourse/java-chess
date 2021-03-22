package chess.domain.game.state;

import chess.domain.board.Board2;
import chess.domain.piece.PieceColor;

public class BlackTurnState extends PlayingState {

    protected BlackTurnState(Board2 board) {
        super(board, PieceColor.BLACK);
    }

    @Override
    protected GameState otherTurnState() {
        return new WhiteTurnState(board());
    }
}
