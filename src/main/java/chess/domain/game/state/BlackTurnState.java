package chess.domain.game.state;

import chess.domain.board.Game;
import chess.domain.piece.PieceColor;

public final class BlackTurnState extends PlayingState {

    public BlackTurnState(Game game) {
        super(game, PieceColor.BLACK);
    }

    @Override
    protected GameState otherTurnState(final Game game) {
        System.out.println("executed black turn");
        return new WhiteTurnState(game);
    }
}
