package controller;

import domain.Chess;
import domain.command.Command;
import domain.command.PlayCommand;
import view.InputView;
import view.OutputView;
import view.mapper.CommandInput;

public class GameMachine {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        outputView.printStartNotice();
        Command command = requestStartCommand();
        if (command.isStart()) {
            Chess chess = new Chess();
            outputView.printBoard(chess.getBoard());
            play(chess);
        }
    }

    private void play(Chess chess) {
        PlayCommand playCommand = requestPlayCommand();
        if (playCommand.isMove()) {
            movePieceByCommand(chess, playCommand);
        }
    }

    private void movePieceByCommand(Chess chess, PlayCommand playCommand) {
        try {
            chess.movePiece(playCommand.sourcePosition(), playCommand.targetPosition());
            outputView.printBoard(chess.getBoard());
            play(chess);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            play(chess);
        }
    }

    private Command requestStartCommand() {
        try {
            String command = inputView.readCommand();
            return CommandInput.asStartCommand(command);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestStartCommand();
        }
    }

    private PlayCommand requestPlayCommand() {
        try {
            String rawCommand = inputView.readCommand();
            return CommandInput.asPlayCommand(rawCommand);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestPlayCommand();
        }
    }
}
