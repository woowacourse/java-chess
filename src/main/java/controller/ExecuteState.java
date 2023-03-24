package controller;

public enum ExecuteState {
    INIT(1), START(1), MOVE(3), END(1);

    private final int commandSize;

    ExecuteState(int commandSize) {
        this.commandSize = commandSize;
    }

    public int getCommandSize() {
        return commandSize;
    }
}
