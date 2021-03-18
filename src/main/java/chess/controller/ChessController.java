package chess.controller;

import chess.domain.BoardInitializer;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static final int COMMAND_INDEX = 0;
    public static final String SPACE = " ";

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandInfo();
        while (chessGame.isRunning()) {
            String inputCmd = InputView.inputCommand();
            Command command = Command.of(splitCommand(inputCmd));
            command.apply(chessGame, inputCmd);
        }
    }

    public static void start(ChessGame chessGame, String command) {
        chessGame.initBoard(BoardInitializer.init());
        OutputView.printBoard(chessGame.getBoard());
    }

    public static void move(ChessGame chessGame, String command) {
        if (chessGame.isReady() || chessGame.isEnd()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았습니다.");
        }
        chessGame.move(command);
        OutputView.printBoard(chessGame.getBoard());
        if (chessGame.isEnd()) {
            OutputView.printTeamWin(chessGame.getWinTeam());
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
