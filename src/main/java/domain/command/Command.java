package domain.command;

import domain.game.Executable;

public interface Command {
    void execute(Executable executable);
}
