package chess.domain.game.state;

import chess.domain.board.Game;
import chess.domain.piece.PieceColor;

public final class WhiteTurnState extends PlayingState {

    public WhiteTurnState(Game game) {
        super(game, PieceColor.WHITE);
    }

    @Override
    protected GameState otherTurnState(final Game game) {
        System.out.println("executed white turn");
        return new BlackTurnState(game);
    }
}
