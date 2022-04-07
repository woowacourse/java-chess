package chess.web.dto;

import chess.domain.state.StateType;

public class BoardDto {

    private final StateType stateType;

    public BoardDto(StateType stateType) {
        this.stateType = stateType;
    }

    public StateType getStateType() {
        return stateType;
    }
}
