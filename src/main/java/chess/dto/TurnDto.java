package chess.dto;

import chess.domain.state.State;

public class TurnDto {

    private final State state;

    public TurnDto(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
