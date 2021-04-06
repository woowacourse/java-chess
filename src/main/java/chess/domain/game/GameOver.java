package chess.domain.game;

public final class GameOver {

    private boolean gameOver;

    public GameOver() {
        gameOver = false;
    }

    public GameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void changeGameOver() {
        gameOver = !gameOver;
    }

}
