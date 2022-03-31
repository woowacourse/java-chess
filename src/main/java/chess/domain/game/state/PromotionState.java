package chess.domain.game.state;

import chess.domain.Color;
import chess.domain.player.Players;

public final class PromotionState extends RunningState {

    public PromotionState(final Players players, final Color color) {
        super(players, color);
    }

    public MoveState run(final String pieceName) {
        players.promotePawn(color, pieceName);
        return new MoveState(players, color.reverse());
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean isPromotable() {
        return true;
    }
}
