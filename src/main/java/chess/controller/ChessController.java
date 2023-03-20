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
    private ChessBoard chessBoard;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        GameState state = new Ready();
        outputView.printStartMessage();
        while (true) {
            state = retryCampPlayIfCommandIllegal(state);
        }
    }

    // TODO 조건문 리팩터링, 중복 코드 없애기
    // TODO 상태 패턴 쓰는 의미가 있나?
    private GameState executeCampPlay(final GameState state) {
        CommandRequest commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.START) {
            GameState nextState = state.start();
            chessBoard = new ChessBoard(Camp.WHITE, Camp::transfer);
            outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
            return nextState;
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            GameState nextState = state.play(chessBoard, commandRequest);
            outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
            return nextState;
        }
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
