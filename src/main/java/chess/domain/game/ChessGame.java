package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void moveAndCatch(final Color color, Position source, Position target) {

    }

}
