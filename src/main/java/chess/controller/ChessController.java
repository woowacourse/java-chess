package chess.controller;

import chess.controller.gamestate.GameState;
import chess.controller.gamestate.Ready;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.dto.CommandRequest;
import chess.util.BoardConverter;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private Camp currentTurnCamp;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.currentTurnCamp = Camp.WHITE;
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        GameState state = new Ready();
        outputView.printStartMessage();
        while (state.shouldRequestUserInput()) {
            state = retryCampPlayIfCommandIllegal(chessBoard, state);
        }
    }

    private GameState executeCampPlay(final ChessBoard chessBoard, final GameState state) {
        CommandRequest commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.END) {
            return state.end();
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            GameState nextState = state.play(chessBoard, commandRequest, currentTurnCamp);
            currentTurnCamp = currentTurnCamp.transfer();
            outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
            return nextState;
        }
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return state.start();
    }

    private GameState retryCampPlayIfCommandIllegal(final ChessBoard chessBoard, final GameState state) {
        try {
            return executeCampPlay(chessBoard, state);
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            outputView.printInputErrorMessage(exception.getMessage());
            return retryCampPlayIfCommandIllegal(chessBoard, state);
        }
    }
}
