package chess;

public enum GameSwitch {
    ON,
    OFF,
    ;

    public boolean isOn() {
        return this == ON;
    }

    public GameSwitch off() {
        return OFF;
    }
}
