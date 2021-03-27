package chess.controller;

import chess.domain.ChessGame;
import chess.domain.CommandType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;

public class ChessController {
    private static final String COMMAND_ERROR = "[ERROR] 올바른 명령이 아닙니다.";

//    public void run() {
//        try {
//            OutputView.printStartMessage();
//            CommandType commandType = CommandType.findFirstCommand(InputView.inputCommand());
//            selectFirstCommand(commandType);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            run();
//        }
//    }
//
//    private void selectFirstCommand(CommandType commandType) {
//        if (commandType == CommandType.START) {
//            ChessGame chessGame = new ChessGame();
//            OutputView.printChessBoard(chessGame.getCurrentPieces());
//            gamePlay(chessGame);
//        }
//    }
//
//    public void gamePlay(ChessGame chessGame) {
//        try {
//            playByCommand(chessGame);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            gamePlay(chessGame);
//        }
//    }
//
//    private void playByCommand(ChessGame chessGame) {
//        CommandType commandType;
//        do {
//            String[] splitInput = InputView.inputCommand().split(" ");
//            commandType = CommandType.findRunningCommand(splitInput[0]);
//            selectRunningCommand(chessGame, commandType, splitInput);
//        }while (chessGame.isRunnable(commandType));
//    }
//
//    private void selectRunningCommand(ChessGame chessGame, CommandType commandType, String[] splitInput) {
//        if (commandType == CommandType.MOVE) {
//            validateRemainingCommand(splitInput);
//            chessGame.play(Arrays.asList(splitInput[1], splitInput[2]));
//            OutputView.printChessBoard(chessGame.getCurrentPieces());
//        }
//        if (commandType == CommandType.END) {
//            return;
//        }
//        if (commandType == CommandType.STATUS) {
//            OutputView.printStatus(chessGame.getCurrentPieces());
//        }
//    }
//
//    private void validateRemainingCommand(String[] splitInput) {
//        if (splitInput.length != 3 || (splitInput[1].length() != 2 && splitInput[2].length() != 2)) {
//            throw new IllegalArgumentException(COMMAND_ERROR);
//        }
//    }
}
