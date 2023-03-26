package controller.command;

import java.util.List;

public interface Command {

    void playTurn(final List<String> inputs);

    boolean isKeepGaming();
}
