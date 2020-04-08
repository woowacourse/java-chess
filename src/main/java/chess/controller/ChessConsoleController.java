package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionDto;
import chess.domain.position.Positions;
import chess.view.ConsoleOutputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessConsoleController {
    private static OutputView outputView = new ConsoleOutputView();

    public static void start(ChessManager chessManager, String input) {
        chessManager.start();
        printBoard(chessManager.getBoard());
    }

    public static void end(ChessManager chessManager, String input) {
        chessManager.end();
    }

    public static void move(ChessManager chessManager, String input) {
        List<String> moveCommand = Arrays.asList(input.split(" "));
        chessManager.move(moveCommand.get(1), moveCommand.get(2));
        printBoard(chessManager.getBoard());

        if (!chessManager.isPlaying()) {
            outputView.printWinner(chessManager.getWinner());
        }
    }

    public static void status(ChessManager chessManager, String input) {
        outputView.printStatus(chessManager.calculateScore(), chessManager.getCurrentTeam());
    }

    private static void printBoard(BoardDto boardDto) {
        PositionDto positionDto = new PositionDto(Positions.get());
        outputView.printBoard(positionDto.get(), boardDto.get());
    }
}
