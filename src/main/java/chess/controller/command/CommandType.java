package chess.controller.command;

import java.util.Collections;
import java.util.List;

public enum CommandType {

    CREATE, LOAD, WAIT, START, END, MOVE, STATUS, EMPTY;

    public List<CommandType> getNextPossibleCommandTypes(boolean isGameEnd){
        if(this == CREATE || this == LOAD){
            return List.of(START, END, MOVE, STATUS);
        }
        if(this == WAIT){
            return List.of(START, END);
        }
        if(this == START){
            return List.of(END, MOVE, STATUS);
        }
        if(this == MOVE && !isGameEnd){
            return List.of(END, MOVE, STATUS);
        }
        if(this == MOVE){
            return List.of(END);
        }
        if(this == STATUS && !isGameEnd){
            return List.of(END, MOVE, STATUS);
        }
        if(this == STATUS){
            return List.of(END);
        }
        return Collections.emptyList();
    }
}
