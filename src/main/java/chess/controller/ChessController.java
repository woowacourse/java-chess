package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandInfo();
        while (chessGame.isRunning()) {
            final Commands inputCmd = InputView.inputCommand();
            Command command = Command.of(inputCmd.mainCommand());
            command.apply(chessGame, inputCmd);
            view(chessGame, command);
        }
    }

    private void view(ChessGame chessGame, Command command) {
        if (command.isStart()) {
            OutputView.printBoard(chessGame.boardDto());
        }
        if (command.isMove()) {
            OutputView.printBoard(chessGame.boardDto());
            confirmKingDead(chessGame);
        }
        if (command.isStatus()) {
            OutputView.printStatus(chessGame.pointDto());
        }
    }

    private void confirmKingDead(ChessGame chessGame) {
        if (chessGame.isEnd()) {
            OutputView.printWinner(chessGame.winner());
        }
    }
}
