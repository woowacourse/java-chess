package chess.domain.command;

import chess.controller.ChessController;

import java.util.Arrays;

public enum FirstCommand {
    START("start", FirstStep.START, Running.NONE),
    END("end", FirstStep.END, Running.END),
    MOVE("move", FirstStep.NONE, Running.MOVE),
    STATUS("status", FirstStep.NONE, Running.STATUS);

    private final String firstCommand;
    private final FirstStep firstStepAction;
    private final Running runningAction;

    FirstCommand(String firstCommand, FirstStep firstStepAction, Running runningAction) {
        this.firstCommand = firstCommand;
        this.firstStepAction = firstStepAction;
        this.runningAction = runningAction;
    }

    public static FirstCommand findFirstCommand(String command) {
        return Arrays.stream(values())
                .filter(value -> value.firstCommand.equals(command))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 잘못된 명령어 입니다."));
    }

    public void runFirstAction(ChessController chessController) {
        this.firstStepAction.accept(chessController);
    }

    public void runRunningAction(ChessController chessController, Command command) {
        this.runningAction.accept(chessController, command);
    }
}
