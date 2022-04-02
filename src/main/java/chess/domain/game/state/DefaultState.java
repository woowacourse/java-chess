package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;

public abstract class DefaultState implements State {
    protected final ChessGame chessGame;
    protected final StateType stateType;

    public DefaultState(StateType stateType, ChessGame chessGame) {
        this.stateType = stateType;
        this.chessGame = chessGame;
    }

    public StateType getType() {
        return stateType;
    }
}
