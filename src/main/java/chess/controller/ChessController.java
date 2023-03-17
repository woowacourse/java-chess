package chess.controller;

import chess.controller.state.Start;
import chess.controller.state.State;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private State state;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.state = new Start();
    }

    public void run() {
        outputView.printStartMessage();
        play();
    }

    private void play() {
        while (state.isNotEnd()) {
            changeState(inputView.requestGameCommand());
        }
    }

    private void changeState(CommandRequest commandRequest) {
        if (commandRequest.getCommand() == Command.START) {
            start();
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            progress(commandRequest);
        }
        if (commandRequest.getCommand() == Command.END) {
            end();
        }
    }

    private void start() {
        state = state.start();
        outputView.printBoard(state.getBoard());
    }

    private void progress(CommandRequest commandRequest) {
        state.move(commandRequest.getSource(), commandRequest.getDestination());
        outputView.printBoard(state.getBoard());
    }

    private void end() {
        state = state.end();
    }

}
