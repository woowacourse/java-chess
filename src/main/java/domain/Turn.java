package domain;

public class Turn {
    TeamColor currentTurn;

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
