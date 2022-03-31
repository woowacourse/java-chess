package chess.domain.game;

public class GameSwitch {

    private boolean value;

    public GameSwitch(final boolean value) {
        this.value = value;
    }

    public void turnOff() {
        this.value = false;
    }

    public boolean isOn() {
        return value;
    }
}
