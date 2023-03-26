package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import service.ChessService;

import java.util.Collections;
import java.util.List;

public final class GameStatus {

    private static final int COMMAND_INDEX = 0;
    private static final String NEW = "new";
    private static final String LOAD = "load";
    private static final String END = "end";

    private final ChessService chessService;
    private Command command;

    public GameStatus(final ChessService chessService) {
        this.chessService = chessService;
    }

    public boolean isKeepGaming() {
        if (command != null) {
            return command.isKeepGaming();
        }
        return true;
    }

    public void playTurn(final List<String> inputs) {
        validateNull(inputs);
        transition(inputs);
        command.playTurn(inputs);
    }

    public void noticeKingDead() {
        if (!chessService.isKingAlive()) {
            this.command = CommandFactory.createCommand(END, chessService);
            command.playTurn(Collections.emptyList());
            chessService.startNewGame();
        }
    }

    private void validateNull(final List<String> inputs) {
        final String command = inputs.get(COMMAND_INDEX);

        if (this.command == null && isNotAllowCommand(command)) {
            throw new IllegalArgumentException(String.format("%s 혹은 %s 명령어를 입력해 주세요", NEW, LOAD));
        }
    }

    private boolean isNotAllowCommand(final String command) {
        return !(command.equalsIgnoreCase(NEW) || command.equalsIgnoreCase(LOAD) || command.equalsIgnoreCase(END));
    }

    private void transition(final List<String> inputs) {
        this.command = CommandFactory.createCommand(inputs.get(COMMAND_INDEX), chessService);
    }

}
