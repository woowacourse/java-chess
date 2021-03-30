package chess.domain.state;

import chess.domain.command.Command;

public class End implements State {

    @Override
    public State action(Command command) {
       if (command.isStatus())  {
           return new Status();
       }
       return this;
    }
}
