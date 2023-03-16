package chess.controller;

import chess.domain.Board;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.ChessInputDto;
import chess.view.GameState;
import chess.view.InputView;
import chess.view.OutputView;

public final class Controller {
    public void run() {
        OutputView.printStartMessage();

        State state = new Ready(Board.create());
        while (!state.isEnd()) {
            state = processGame(state);
        }
    }

    private State processGame(final State state) {
        try {
            final ChessInputDto chessInputDto = InputView.inputGameState();
            final GameState gameState = chessInputDto.getGameState();

            return processState(state, chessInputDto, gameState);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return state.end();
    }

    private State processState(final State state, final ChessInputDto chessInputDto, final GameState gameState) {
        State newState = state;
        if (gameState == GameState.START) {
            newState = state.start();
        }
        if (gameState == GameState.END) {
            newState = state.end();
        }
        if (gameState == GameState.MOVE) {
            newState = state.move(chessInputDto.getSource(), chessInputDto.getTarget());
        }
        OutputView.printChessBoard(state.getBoard().getBoard());
        return newState;
    }
}
