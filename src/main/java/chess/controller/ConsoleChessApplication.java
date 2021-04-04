package chess.controller;

import chess.domain.chess.Chess;
import chess.domain.chess.Color;
import chess.domain.position.MovePosition;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();

        Chess chess = Chess.createWithEmptyBoard();
        while (!chess.isTerminated()) {
            chess = executeCommand(chess);
        }
    }

    private static Chess executeCommand(Chess chess) {
        try {
            final Command command = Command.findCommandByInputCommand(InputView.askCommand());
            chess = command.execute(chess);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chess;
    }

    public static Chess start(Chess chess) {
        chess = chess.start();
        OutputView.printBoard(chess);
        return chess;
    }

    public static Chess move(Chess chess) {
        String[] positions = InputView.askPositions();
        chess = chess.move(MovePosition.from(positions));
        OutputView.printBoard(chess);
        if (chess.isKindDead()) {
            OutputView.printKingIsDead(chess.winner());
        }
        return chess;
    }

    public static Chess status(Chess chess) {
        double blackScore = chess.score(Color.BLACK);
        double whiteScore = chess.score(Color.WHITE);
        if (chess.isRunning()) {
            OutputView.printStatusWithRunningMessage(blackScore, whiteScore);
        }

        if (chess.isKindDead() || chess.isStop()) {
            OutputView.printStatusWithWinner(blackScore, whiteScore, chess.winner());
        }
        return chess;
    }

    public static Chess end(Chess chess) {
        if (chess.isKindDead() || chess.isStop()) {
            OutputView.printChessIsStopping();
        }

        chess = chess.end();
        OutputView.printGameIsStopped();
        return chess;
    }

    public static Chess exit(Chess chess) {
        chess = chess.exit();
        OutputView.printExit();
        return chess;
    }
}
