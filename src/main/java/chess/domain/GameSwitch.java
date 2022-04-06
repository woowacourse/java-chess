package chess.domain;

public enum GameSwitch {
    ON,
    OFF,
    ;

    public GameSwitch off() {
        return OFF;
    }

    public boolean isOn() {
        return this == ON;
    }

    public boolean isOff() {
        return this == OFF;
    }
}
