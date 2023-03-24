package chess.controller;

import chess.domain.Board;
import chess.domain.state.Start;
import chess.domain.state.State;
import chess.dto.ChessInputDto;
import chess.view.GameState;
import chess.view.InputView;
import chess.view.OutputView;

public final class Controller {
    public void run() {
        OutputView.printStartMessage();
        State state = new Start(Board.create());
        while (state.isNotEnd()) {
            final ChessInputDto dto = InputView.inputGameState();
            state = processChess(state, dto);
            OutputView.printChessBoard(state.getBoard());
        }
    }

    private State processChess(State state, final ChessInputDto dto) {
        final GameState gameState = dto.getGameState();
        if (gameState == GameState.START) {
            return state.start();
        }
        if (gameState == GameState.MOVE) {
            return state.move(dto);
        }
        if (gameState == GameState.END) {
            return state.end();
        }
        return state;
    }
}
