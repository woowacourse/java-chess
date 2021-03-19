package chess.controller;

import chess.service.ChessService;
import chess.service.Command;
import chess.service.state.GameState;
import chess.service.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final ChessService chessService;
    private GameState gameState;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printChessInstruction();
        playRounds();
    }

    private void playRounds() {
        while (!chessService.isGameOver()) {
            playRound();
        }
    }

    private void playRound() {
        changeStateByCommand();
        this.gameState = gameState.playRound(chessService);
    }

    private void changeStateByCommand() {
        String command = InputView.command();
        gameState = Command.gameState(command);
    }
}
