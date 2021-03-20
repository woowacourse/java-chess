package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Result;
import chess.domain.piece.TeamType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {

    public static TeamType teamType = TeamType.WHITE;

    public static void main(String[] args) {
        Command command = getFirstCommand();
        if (command == Command.START) {
            run();
        }
    }

    private static Command getFirstCommand() {
        InputView.printGameStartMessage();
        List<String> playerCommand = InputView.inputPlayerCommand();
        return Command.findCommand(playerCommand.get(0));
    }

    private static void run() {
        Board board = initializeBoard();
        OutputView.printBoard(board);
        startChessGame(board);
        if (board.isKingCheckmate()) {
            OutputView.printWinner(board.winner());
        }
    }

    private static void startChessGame(Board board) {
        Command command = Command.START;
        while (command != Command.END && !board.isKingCheckmate()) {
            List<String> playerCommand = InputView.inputPlayerCommand();
            command = Command.findCommand(playerCommand.get(0));
            startChessGame(command, board, playerCommand);
            OutputView.printBoard(board);
        }
    }

    private static Board initializeBoard() {
        Board board = Board.getInstance();
        board.initialize();
        return board;
    }

    private static void startChessGame(Command command, Board board, List<String> playerCommand) {
        if (command == Command.MOVE) {
            move(board, playerCommand);
            return;
        }
        if (command == Command.STATUS) {
            Result result = board.calculateScores();
            OutputView.printScoreResult(result);
        }
    }

    private static void move(Board board, List<String> playerCommand) {
        Coordinate currentCoordinate = Coordinate.from(playerCommand.get(1));
        Coordinate targetCoordinate = Coordinate.from(playerCommand.get(2));
        board.move(currentCoordinate, targetCoordinate, teamType);
        teamType = teamType.getOppositeTeam();
    }
}
