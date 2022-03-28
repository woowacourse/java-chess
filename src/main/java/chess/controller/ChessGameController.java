package chess.controller;

import chess.domain.ChessGame;
import chess.command.Command;
import chess.command.Move;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame();
        Command command;
        do {
            command = inputView.readCommand();
            executeCommand(chessGame, command);
        } while (!command.isEnd() && !chessGame.isFinished());
    }

    private void executeCommand(ChessGame chessGame, Command command) {
        if (command.isStart()) {
            start(chessGame);
        }
        if (command.isMove()) {
            move(chessGame, (Move) command);
        }
        if (command.isEnd()) {
            end(chessGame);
        }
        if (command.isStatus()) {
            outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
        }
    }

    private void start(ChessGame chessGame) {
        chessGame.start();
        outputView.printBoard(chessGame.getBoard().getValue());
    }

    private void move(ChessGame chessGame, Move move) {
        chessGame.move(move.getSourcePosition(), move.getTargetPosition());
        outputView.printBoard(chessGame.getBoard().getValue());
        if (chessGame.isFinished()) {
            outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
            printResult(chessGame);
        }
    }

    private void end(ChessGame chessGame) {
        outputView.printFinishMessage();
        chessGame.end();
        if (chessGame.isFinished()) {
            outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
            printResult(chessGame);
        }
    }

    private void printResult(ChessGame chessGame) {
        if (chessGame.hasBlackWon() > 0) {
            outputView.printBlackWin();
        }
        if (chessGame.hasBlackWon() < 0) {
            outputView.printWhiteWin();
        }
        if (chessGame.hasBlackWon() == 0) {
            outputView.printDraw();
        }
    }
}
