package domain.game;

public class Turn {
    private TeamColor currentTurn;

    public Turn() {
        this.currentTurn = TeamColor.WHITE;
    }

    public void next() {
        currentTurn = currentTurn.toggle();
    }

    public TeamColor current() {
        return currentTurn;
    }
}
