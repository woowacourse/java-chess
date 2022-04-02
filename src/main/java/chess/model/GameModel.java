package chess.model;

import chess.domain.game.GameState;

public class GameModel {

    private final int id;
    private final GameState state;

    public GameModel(int id, GameState state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }
}
