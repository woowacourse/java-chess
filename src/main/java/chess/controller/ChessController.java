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
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void start() {
        if (inputView.askChessRun()) {
            run();
        }
    }

    public static void run() {
        ChessRunner chessRunner = new ChessRunner();
        printBoard(chessRunner.getBoard());
        boolean moveFlag = true;

        while (moveFlag) {
            try {
                String[] moveSource = inputView.askMoveOrStatus().split(" ");
                if (moveSource[0].toUpperCase().equals("END")) {
                    break;
                }
                if (moveSource[0].toUpperCase().equals("STATUS")) {
                    outputView.printStatus(chessRunner.calculateScore(), chessRunner.getCurrentTeam());
                    continue;
                }
                String sourcePosition = moveSource[1];
                String targetPosition = moveSource[2];
                chessRunner.update(sourcePosition, targetPosition);
                printBoard(chessRunner.getBoard());
                if (!findWinner(chessRunner)) break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean findWinner(ChessRunner chessRunner) {
        Team winner = chessRunner.findWinner();
        if (winner != null) {
            outputView.printWinner(winner);
            return false;
        }
        return true;
    }

    private static void printBoard(Board board) {
        BoardDto boardDto = new BoardDto(board.parse());
        PositionDto positionDto = new PositionDto(Position.getPositions());
        outputView.printBoard(positionDto.getPositions(), boardDto.get());
    }
}
