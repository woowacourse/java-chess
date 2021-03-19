package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView.printGameStartMessage();
        List<String> playerCommand = InputView.inputPlayerCommand();
        Command command = Command.findCommand(playerCommand.get(0));

        Board board = Board.getInstance();
        board.initialize();
        if (command != Command.START && command != Command.END) {
            throw new IllegalArgumentException();
        }
        if (command == Command.START) {
            startChessGame(board);
        }
    }

    private static void startChessGame(Board board) {
        OutputView.printBoard(board);
        List<String> playerCommand = InputView.inputPlayerCommand();
        Command command = Command.findCommand(playerCommand.get(0));

        TeamType teamType = TeamType.WHITE;
        while (command != Command.END) {
            executeCommand(command, board, teamType, playerCommand);
            OutputView.printBoard(board);
            playerCommand = InputView.inputPlayerCommand();
            command = Command.findCommand(playerCommand.get(0));
            if (command == Command.MOVE) {
                teamType = teamType.nextTurn();
            }
        }
    }

    private static void executeCommand(Command command, Board board, TeamType teamType, List<String> playerCommand) {
        if (command == Command.MOVE) {
            Coordinate currentCoordinate = Coordinate.from(playerCommand.get(1));
            Coordinate targetCoordinate = Coordinate.from(playerCommand.get(2));
            board.move(currentCoordinate, targetCoordinate, teamType);
            return;
        }
        if (command == Command.STATUS) {

        }
    }
}
