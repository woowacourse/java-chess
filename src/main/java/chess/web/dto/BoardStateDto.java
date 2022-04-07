package chess.web.dto;

import chess.domain.state.StateType;

public class BoardStateDto {

    private final StateType stateType;

    public BoardStateDto(StateType stateType) {
        this.stateType = stateType;
    }

    public StateType getStateType() {
        return stateType;
    }
}
