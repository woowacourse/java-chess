package chess.controller;

import chess.model.ChessGame;
import chess.model.Turn;
import chess.model.board.BoardFactory;
import chess.model.command.Command;
import chess.model.command.Start;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void start() {
        ChessGame chessGame = new ChessGame(BoardFactory.create());
        OutputView.startGame();
        Command command = new Start(InputView.inputCommand());
        Turn turn = Turn.init();
        run(chessGame, command, turn);
    }

    private void run(ChessGame chessGame, Command command, Turn turn) {
        while (!command.isEnd() || chessGame.isKingDead()) {
            turn = chessGame.progress(command, turn);
            OutputView.printBoard(chessGame.getBoard());
            command = command.turnState(InputView.inputCommand());
        }
        end(chessGame, command);
    }

    private void end(ChessGame chessGame, Command command) {
        OutputView.printFinishMessage();
        command = command.turnFinalState(InputView.inputCommand());
        if (command.isStatus()) {
            OutputView.printFinalResult(chessGame.getWinTeam(), chessGame.getWhiteTeamScore(), chessGame.getBlackTeamScore());
        }
    }
}
