package chess.domain.game;

public class GameSwitch {

    private boolean isOn;

    public GameSwitch() {
        this.isOn = true;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOff() {
        this.isOn = false;
    }
}
