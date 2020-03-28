package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionDto;
import chess.domain.ChessRunner;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String DELIMITER = " ";
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void start() {
        try {
            Command command = Command.of(inputView.askChessRun());
            run(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private static void run(Command command) {
        if (command.isStart()) {
            ChessRunner chessRunner = new ChessRunner();
            printBoard(chessRunner.getBoard());
            runChess(chessRunner);
        }
    }

    public static void runChess(ChessRunner chessRunner) {
        try {
            String input = inputView.askGameCommand();
            Command command = Command.of(input);

            runByCommand(chessRunner, input, command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            runChess(chessRunner);
        }
    }

    private static void runByCommand(ChessRunner chessRunner, String input, Command command) {
        switch (command) {
            case MOVE:
                runMove(chessRunner, input);
                break;
            case STATUS:
                runStatus(chessRunner);
                break;
            case END:
            default:
                break;
        }
    }

    private static void runMove(ChessRunner chessRunner, String input) {
        String[] commands = input.split(DELIMITER);
        String source = commands[SOURCE_INDEX];
        String target = commands[TARGET_INDEX];
        chessRunner.update(source, target);
        printBoard(chessRunner.getBoard());
        if (findWinner(chessRunner)) {
            runChess(chessRunner);
        }
    }

    private static boolean findWinner(final ChessRunner chessRunner) {
        Team winner = chessRunner.findWinner();
        if (winner != null) {
            outputView.printWinner(winner);
            return false;
        }
        return true;
    }

    private static void runStatus(ChessRunner chessRunner) {
        double score = chessRunner.calculateScore();
        outputView.printStatus(score, chessRunner.getCurrentTeam());
        printBoard(chessRunner.getBoard());
        runChess(chessRunner);
    }

    private static void printBoard(final Board board) {
        BoardDto boardDto = new BoardDto(board.parse());
        PositionDto positionDto = new PositionDto(Position.getPositions());
        outputView.printBoard(positionDto.getPositions(), boardDto.get());
    }
}
