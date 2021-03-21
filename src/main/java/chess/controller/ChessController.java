package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private ChessGame chessGame;

    public ChessController() {
        this.chessGame = new ChessGame();
    }

    public void run() {
        try {
            firstStep();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            firstStep();
        }
    }

    private void firstStep() {
        OutputView.printStartMessage();
        Command command = new Command(InputView.command());
        command.runFirstAction(this);
    }

    public void start() {
        OutputView.printChessBoard(chessGame.getCurrentPieces());
        try {
            play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play();
        }
    }

    private void play() {
        while (chessGame.runnable()) {
            OutputView.printRequestCommandMessage();
            Command command = new Command(InputView.command());
            command.runRunningAction(this, command);
        }
    }

    public void except() {
        throw new IllegalStateException("[ERROR] 진행할 수 없는 명령어입니다.");
    }

    public void move(Command command) {
        try {
            chessGame.movePieceFromSourceToTarget(command);
            OutputView.printChessBoard(chessGame.getCurrentPieces());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play();
        }

    }

    public void status() {
        OutputView.printStatus(chessGame.getCurrentPieces());
    }

    public void end() {
        System.exit(0);
    }
}
