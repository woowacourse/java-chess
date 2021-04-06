package chess.domain.game;

import chess.domain.state.Start;
import chess.domain.state.State;

public class WebChessGame {

    private State state;

    public WebChessGame() {
        this.state = new Start();
        state.receive("start");
        state = state.next();
    }
}
