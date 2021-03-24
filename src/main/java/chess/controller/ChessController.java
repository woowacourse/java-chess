package chess.controller;

import chess.domain.ChessGame;
import chess.domain.util.BoardInitializer;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static final int COMMAND_INDEX = 0;
    public static final String SPACE = " ";

    public static void start(ChessGame chessGame) {
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

    public static void end(ChessGame chessGame) {
        chessGame.endGame();
    }

    public static void status(ChessGame chessGame) {
        if (chessGame.isReady()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았습니다.");
        }
        OutputView.printStatus(chessGame.calculatePoint());
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandInfo();
        while (chessGame.isRunning()) {
            try {
                String inputCmd = InputView.inputCommand();
                Command command = Command.of(splitCommand(inputCmd));
                applyCommand(chessGame, command, inputCmd);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void applyCommand(ChessGame chessGame, Command command, String inputCmd) {
        if(command == Command.START) {
            start(chessGame);
        }
        if(command == Command.MOVE) {
            move(chessGame, inputCmd);
        }
        if(command == Command.STATUS) {
            status(chessGame);
        }
        if(command == Command.END) {
            end(chessGame);
        }
    }

    private String splitCommand(String inputCmd) {
        return inputCmd.split(SPACE)[COMMAND_INDEX];
    }
}
