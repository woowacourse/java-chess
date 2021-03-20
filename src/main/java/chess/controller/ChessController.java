package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;


public class ChessController {
    public void run() {
        try {
            ChessGame chessGame = new ChessGame();
            OutputView.printStartMessage();
            Command firstCommand = new Command(InputView.inputRunningCommand());
            firstCommand.validateRightFirstCommand();
            if (firstCommand.isStart()) {
                OutputView.printChessBoard(chessGame.getCurrentPieces());
            }
            if (firstCommand.isEnd()) {
                return;
            }
            play(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }

    }

    public void play(ChessGame chessGame) {
        try {
            while (chessGame.isRunning()) {
                Command command = new Command(InputView.inputRunningCommand());
                command.validateRunningCommand();
                if (command.isEnd()) {
                    break;
                }
                if (command.isMove()) {
                    chessGame.play(command);
                    OutputView.printChessBoard(chessGame.getCurrentPieces());
                }
                if(command.isStatus()) {
                    OutputView.printStatus(chessGame.getCurrentPieces());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }

    }
}
