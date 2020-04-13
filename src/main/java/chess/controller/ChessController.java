package chess.controller;

import chess.domain.ChessRunner;
import chess.dto.TeamDTO;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class ChessController {
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void start() {
        outputView.printGameRule();
        Command command = getGameCommand();
        if (command.isStart()) {
            ChessRunner chessRunner = new ChessRunner();
            GameController gameController = command.getGameController();
            gameController.execute(chessRunner, StringUtils.EMPTY);

            runChessGame(command, chessRunner);
        }
    }

    private static Command getGameCommand() {
        Optional<Command> command = Optional.empty();

        do {
            command = Command.of(inputView.askGameCommand());
        } while (isEmptyCommand(command));
        return command.get();
    }

    private static boolean isEmptyCommand(Optional<Command> command) {
        if (command.isPresent()) {
            return false;
        }

        System.out.println("잘못된 명령어를 입력하였습니다.");
        return true;
    }

    private static void runChessGame(Command command, ChessRunner chessRunner) {
        do {
            command = validateExecute(command, chessRunner);
        } while (!command.isEnd() && !chessRunner.isEndChess());
        printWinner(chessRunner);
    }

    private static Command validateExecute(Command command, ChessRunner chessRunner) {
        try {
            command = executeByCommand(command, chessRunner);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return command;
    }

    private static Command executeByCommand(Command command, ChessRunner chessRunner) {
        String input = inputView.askGameCommand();
        Optional<Command> inputCommand = Command.of(input);

        while (isEmptyCommand(inputCommand)) {
            input = inputView.askGameCommand();
            inputCommand = Command.of(input);
        }
        command = inputCommand.get();
        command.execute(chessRunner, input);
        return command;
    }

    private static void printWinner(ChessRunner chessRunner) {
        if (chessRunner.isEndChess()) {
            TeamDTO teamDto = new TeamDTO(chessRunner.getWinner());
            outputView.printWinner(teamDto.getTeamName());
        }
    }
}
