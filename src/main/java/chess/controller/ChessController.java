package chess.controller;

import chess.domain.ChessGame;
import chess.domain.piece.Color;
import chess.view.MoveCommand;
import chess.view.OutputView;

import java.util.Arrays;

import static chess.view.Command.*;
import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printStartMessage;

public class ChessController {

    private static final String EXCEPTION_WRONG_COMMAND = "잘못된 명령어 입력입니다.";

    public void run() {
        printStartMessage();
        ChessGame chessGame = new ChessGame();
        while (!chessGame.isFinished()) {
            String input = inputCommand();
            executeCommand(chessGame, input);
        }
        OutputView.printFinalScore(chessGame.computeScore(Color.BLACK), chessGame.computeScore(Color.WHITE));
    }

    private void executeCommand(ChessGame chessGame, String input) {
        if (!isExistCommand(input)) {
            throw new IllegalArgumentException(EXCEPTION_WRONG_COMMAND);
        }
        if (STATUS.isValue(input)) {
            OutputView.printProgressScore(chessGame.computeScore(Color.BLACK), chessGame.computeScore(Color.WHITE));
            return;
        }
        playTurn(chessGame, input);
    }

    private void playTurn(ChessGame chessGame, String input) {
        if (START.isValue(input)) {
            chessGame.start();
        }
        if (MOVE.isValue(input)) {
            executeMove(chessGame, input);
        }
        if (END.isValue(input)) {
            chessGame.end();
        }
        OutputView.printBoard(chessGame.getChessBoard());
    }

    private void executeMove(ChessGame chessGame, String input) {
        MoveCommand moveCommand = new MoveCommand(input);
        chessGame.move(moveCommand.getSource(), moveCommand.getTarget());
    }

    private boolean isExistCommand(String input) {
        return Arrays.stream(values())
                .anyMatch(command -> command.isValue(input));
    }
}
