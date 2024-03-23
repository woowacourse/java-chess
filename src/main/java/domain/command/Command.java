package domain.command;

import domain.game.Execute;

public interface Command {
    void execute(Execute execute);
}
