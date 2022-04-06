package chess.web.dto;

import chess.domain.state.StateType;
import java.util.Objects;

public class TurnDto {

    private final StateType turn;

    public TurnDto(StateType turn) {
        this.turn = turn;
    }

    public StateType getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TurnDto turnDto = (TurnDto) o;
        return turn == turnDto.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
