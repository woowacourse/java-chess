package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.controller.gamestate.GameState;
import chess.controller.gamestate.Ready;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        GameState state = new Ready();
        outputView.printGuideMessage();
        while (true) {
            state = retryCampPlayIfCommandIllegal(state);
        }
    }

    // TODO 조건문 리팩터링, 중복 코드 없애기
    private GameState executeCampPlay(final GameState state) {
        CommandRequest commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.START) {
            GameState running = state.start(new ChessBoard(Camp.WHITE, Camp::transfer));
            outputView.printBoard(BoardConverter.convertToBoard(running.read()));
            return running;
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            GameState running = state.play(commandRequest);
            outputView.printBoard(BoardConverter.convertToBoard(running.read()));
            return running;
        }
        outputView.printGuideMessage();
        return state.end();
    }

    private GameState retryCampPlayIfCommandIllegal(final GameState state) {
        try {
            return executeCampPlay(state);
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            outputView.printInputErrorMessage(exception.getMessage());
            return retryCampPlayIfCommandIllegal(state);
        }
    }

}
