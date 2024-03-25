package model;

public enum ChessStatus {
    INIT,
    RUNNING,
    END;

    public boolean isNotEnd() {
        return END != this;
    }
}
