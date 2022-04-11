package chess.controller;

import chess.dao.GameStateDao;

public class FakeGameStateDao implements GameStateDao {

    private String gameState;
    private String turn;

    public FakeGameStateDao() {
        this.gameState = "nothing";
        this.turn = "nothing";
    }

    @Override
    public boolean hasPlayingGame() {
        return !gameState.equals("nothing");
    }

    @Override
    public void saveState(final String state) {
        this.gameState = state;
    }

    @Override
    public void saveTurn(final String turn) {
        this.turn = turn;
    }

    @Override
    public String getGameState() {
        return gameState;
    }

    @Override
    public String getTurn() {
        return turn;
    }

    @Override
    public void removeGameState() {
        gameState = "nothing";
        turn = "nothing";
    }
}
