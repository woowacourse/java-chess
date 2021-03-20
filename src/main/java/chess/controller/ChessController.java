package chess.controller;

import chess.domain.util.BoardInitializer;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static final int COMMAND_INDEX = 0;
    public static final String SPACE = " ";

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandInfo();
        while (chessGame.isRunning()) {
            try {
                String inputCmd = InputView.inputCommand();
                Command command = Command.of(splitCommand(inputCmd));
                command.apply(chessGame, inputCmd);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void start(ChessGame chessGame, String command) {
        chessGame.initBoard(BoardInitializer.init());
        OutputView.printBoard(chessGame.getPrintBoardDto());
    }

    public static void move(ChessGame chessGame, String command) {
        try {
            if (chessGame.isReady() || chessGame.isEnd()) {
                throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았거나 종료되었습니다.");
            }
            chessGame.move(command);
            OutputView.printBoard(chessGame.getPrintBoardDto());
            if (chessGame.isEnd()) {
                OutputView.printTeamWin(chessGame.getWinTeam());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            OutputView.printBoard(chessGame.getPrintBoardDto());
        }
    }

    public static void end(ChessGame chessGame, String command) {
        chessGame.endGame();
    }

    public static void status(ChessGame chessGame, String command) {
        if (chessGame.isReady()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았습니다.");
        }
        OutputView.printStatus(chessGame.calculatePoint());
    }

    private String splitCommand(String inputCmd) {
        return inputCmd.split(SPACE)[COMMAND_INDEX];
    }
}
