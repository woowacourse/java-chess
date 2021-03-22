package chess.domain.game.state;

import chess.domain.board.Board2;
import chess.domain.piece.PieceColor;

public class WhiteTurnState extends PlayingState {

    protected WhiteTurnState(Board2 board) {
        super(board, PieceColor.WHITE);
    }

    @Override
    protected GameState otherTurnState() {
        return new BlackTurnState(board());
    }
}
