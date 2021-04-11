package chess.domain.player;

import chess.domain.state.State;

public class WhitePlayer extends Player {
    private final String name = "white";

    public WhitePlayer(final State state) {
        super(state);
    }

    @Override
    public String getName() {
        return name;
    }
}
