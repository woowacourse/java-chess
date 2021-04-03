package chess.domain.player;

import chess.domain.state.State;

public class BlackPlayer extends Player {
    private final String name = "black";

    public BlackPlayer(final State state) {
        super(state);
    }

    @Override
    public String getName() {
        return name;
    }
}
