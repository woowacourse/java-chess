package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.EndStatus;

import java.util.List;

public class QuitCommand implements Command {

    @Override
    public ChessProgramStatus executeStart() {
        return new EndStatus();
    }

    @Override
    public ChessProgramStatus executePlay(final List<String> command, final int gameId) {
        return new EndStatus();
    }
}
