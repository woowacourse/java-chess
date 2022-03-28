package chess.controller;

import chess.domain.ChessGame;
import chess.domain.piece.Color;
import chess.view.OutputView;

import java.util.Arrays;

import static chess.Command.*;
import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printStartMessage;

public class ChessController {

    private static final String ERROR_WRONG_COMMAND = "잘못된 명령어 입력입니다.";

    public void run() {
        ChessGame chessGame = new ChessGame();
        printStartMessage();
        while (!chessGame.isFinished()) {
            playTurn(chessGame);
        }
        OutputView.printFinalScore(chessGame.computeScore(Color.BLACK),chessGame.computeScore(Color.WHITE));
    }

    private void playTurn(ChessGame chessGame) {
        String input = inputCommand();
        if (START.isValue(input)) {
            chessGame.start();
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (MOVE.isValue(input)) {
            chessGame.move(input);
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (STATUS.isValue(input)) {
            OutputView.printProgressScore(chessGame.computeScore(Color.BLACK), chessGame.computeScore(Color.WHITE));
        }
        if (END.isValue(input)) {
            chessGame.end();
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (!isExistCommand(input)) {
            throw new IllegalArgumentException(ERROR_WRONG_COMMAND);
        }
    }

    private boolean isExistCommand(String input) {
        return Arrays.stream(values())
                .anyMatch(command -> command.isValue(input));
    }
}
