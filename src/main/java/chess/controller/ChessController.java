package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        try {
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private void start() {
        ChessGame chessGame = new ChessGame();
        OutputView.printStartMessage();
        Command firstCommand = new Command(InputView.command());
        if (chessGame.startAble(firstCommand)) {
            OutputView.printChessBoard(chessGame.getCurrentPieces());
            play(chessGame);
        }
        end();
    }

    public void play(ChessGame chessGame) {
        try {
            while (chessGame.isRunning()) {
                Command command = new Command(InputView.command());
                command.validateRunningCommand();
                if (command.isEnd()) {
                    break;
                }
                if (command.isMove()) {
                    chessGame.play(command);
                    OutputView.printChessBoard(chessGame.getCurrentPieces());
                }
                if (command.isStatus()) {
                    OutputView.printStatus(chessGame.getCurrentPieces());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }
    }

    private void end() {
        return;
    }
}
