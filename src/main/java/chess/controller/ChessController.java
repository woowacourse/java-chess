package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionDto;
import chess.domain.ChessRunner;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Positions;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void start() {
        if (inputView.askStartCommand()) {
            run();
        }
    }

    public static void run() {
        ChessRunner chessRunner = new ChessRunner();
        printBoard(chessRunner.getBoard());
        boolean moveFlag = true;

        while (moveFlag) {
            try {
                List<String> runCommand = inputView.askRunCommand().get();
                if (runCommand.get(0).toUpperCase().equals("END")) {
                    break;
                }
                if (runCommand.get(0).toUpperCase().equals("STATUS")) {
                    outputView.printStatus(chessRunner.calculateScore(), chessRunner.getCurrentTeam());
                    continue;
                }
                String sourcePosition = runCommand.get(1);
                String targetPosition = runCommand.get(2);
                chessRunner.update(sourcePosition, targetPosition);
                printBoard(chessRunner.getBoard());
                if (findWinner(chessRunner)) {
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean findWinner(ChessRunner chessRunner) {
        Team winner = chessRunner.findWinner();
        if (winner != null) {
            outputView.printWinner(winner);
            return true;
        }
        return false;
    }

    private static void printBoard(Board board) {
        BoardDto boardDto = new BoardDto(board.parse());
        PositionDto positionDto = new PositionDto(Positions.get());
        outputView.printBoard(positionDto.get(), boardDto.get());
    }
}
