package chess.controller;

public enum Command {

    START, END, MOVE, STATUS;

    public boolean isPlayable(){
        return this != END;
    }
}
