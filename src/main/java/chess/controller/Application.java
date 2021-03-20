package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Result;
import chess.domain.player.TeamType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {

    public static TeamType teamType = TeamType.WHITE;

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

        Command command = Command.START;
        while (command != Command.END && !board.isKingCheckmate()) {
            List<String> playerCommand = InputView.inputPlayerCommand();
            command = Command.findCommand(playerCommand.get(0));
            executeCommand(command, board, playerCommand);
            OutputView.printBoard(board);
        }
        OutputView.printWinner(board.winner());
    }

    private static void executeCommand(Command command, Board board, List<String> playerCommand) {
        if (command == Command.MOVE) {
            Coordinate currentCoordinate = Coordinate.from(playerCommand.get(1));
            Coordinate targetCoordinate = Coordinate.from(playerCommand.get(2));
            board.move(currentCoordinate, targetCoordinate, teamType);
            teamType = teamType.nextTurn();
            return;
        }
        if (command == Command.STATUS) {
            Result result = board.calculateScores();
            OutputView.printScoreResult(result);
        }
    }
}
