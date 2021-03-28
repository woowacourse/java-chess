package chess.domain.player;

import chess.domain.state.State;

public class BlackPlayer extends Player {
    private final String name = "검정이";

    public BlackPlayer(final State state) {
        super(state);
    }

    @Override
    public String getName() {
        return name;
    }
}
