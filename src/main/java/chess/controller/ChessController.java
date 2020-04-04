package chess.controller;

import chess.controller.dto.TeamDto;
import chess.domain.ChessRunner;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;
import org.apache.commons.lang3.StringUtils;

public class ChessController {
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void start() {
        Command command = getCommand();
        if (command.isStart()) {
            ChessRunner chessRunner = new ChessRunner();
            GameController gameController = command.getGameController();
            gameController.execute(chessRunner, StringUtils.EMPTY);

            runChessGame(command, chessRunner);
        }
    }

    private static Command getCommand() {
        try {
            return Command.of(inputView.askChessRun());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCommand();
        }
    }

    private static void runChessGame(Command command, ChessRunner chessRunner) {
        do {
            command = validateExecute(command, chessRunner);
        } while (!command.isEnd() && !chessRunner.isEndChess());
        printWinner(chessRunner);
    }

    private static Command validateExecute(Command command, ChessRunner chessRunner) {
        try {
            String commands = inputView.askGameCommand();
            command = Command.of(commands);
            GameController gameController = command.getGameController();
            gameController.execute(chessRunner, commands);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return command;
    }

    private static void printWinner(ChessRunner chessRunner) {
        if (chessRunner.isEndChess()) {
            TeamDto teamDto = new TeamDto(chessRunner.getWinner());
            outputView.printWinner(teamDto.getTeamName());
        }
    }
}
